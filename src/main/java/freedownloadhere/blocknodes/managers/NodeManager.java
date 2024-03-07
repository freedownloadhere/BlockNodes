package freedownloadhere.blocknodes.managers;

import freedownloadhere.blocknodes.command.*;
import freedownloadhere.blocknodes.node.Node;
import freedownloadhere.blocknodes.node.NodeScene;
import freedownloadhere.blocknodes.node.action.KeyInputAction;
import freedownloadhere.blocknodes.node.action.KeybindingInputAction;
import freedownloadhere.blocknodes.node.action.MouseInputAction;
import freedownloadhere.blocknodes.node.action.NodeAction;
import freedownloadhere.blocknodes.scenepersistence.ScenePersistenceManager;
import freedownloadhere.blocknodes.utils.Log;
import freedownloadhere.blocknodes.utils.PlayerPosHelper;
import freedownloadhere.blocknodes.utils.Vector3i;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import java.util.*;

public class NodeManager
{
    private static NodeManager Instance;
    private final HashMap<String, NodeScene> LoadedNodeScenes;
    private final LinkedList<NodeAction> ActionQueue;

    private NodeScene CurrentScene;
    private Node CurrentNode;

    public static void Instantiate()
    {
        Instance = new NodeManager();
        NodeAction.Initialize();
        KeyInputAction.Initialize();
        MouseInputAction.Initialize();
        KeybindingInputManager.Initialize();

        ClientCommandHandler.instance.registerCommand(new AddSceneCommand());
        ClientCommandHandler.instance.registerCommand(new SetSceneCommand());
        ClientCommandHandler.instance.registerCommand(new LoadSceneCommand());
        ClientCommandHandler.instance.registerCommand(new SaveSceneCommand());
        ClientCommandHandler.instance.registerCommand(new AddNodeCommand());
        ClientCommandHandler.instance.registerCommand(new AddNodeActionCommand());
        ClientCommandHandler.instance.registerCommand(new KBIMCommand());
        MinecraftForge.EVENT_BUS.register(Instance);
    }
    public static NodeManager GetInstance()
    {
        return Instance;
    }
    private NodeManager()
    {
        LoadedNodeScenes = new HashMap<>();
        ActionQueue = new LinkedList<>();
    }

    public boolean AddEmptyScene(String name)
    {
        if(!LoadedNodeScenes.containsKey(name))
        {
            LoadedNodeScenes.put(name, new NodeScene(name));
            return true;
        }

        Log.Error("Scene: \u00A7e " + name + "\u00A7r already exists!");
        return false;
    }
    public boolean SetScene(String name)
    {
        if(LoadedNodeScenes.containsKey(name))
        {
            CurrentScene = LoadedNodeScenes.get(name);
            return true;
        }

        Log.Error("Scene: \u00A7e " + name + "\u00A7r does not exist!");
        return false;
    }
    public boolean LoadScene(String name)
    {
        NodeScene loadedScene = ScenePersistenceManager.LoadScene(name);
        if(loadedScene == null)
        {
            Log.Error("Scene: \u00A7e " + name + "\u00A7r could not be loaded!");
            return false;
        }

        LoadedNodeScenes.put(name, loadedScene);
        return true;
    }
    public boolean SaveScene(String name)
    {
        if (!LoadedNodeScenes.containsKey(name))
        {
            Log.Error("Scene: \u00A7e " + name + "\u00A7r cannot be saved, as it does not exist!");
            return false;
        }

        ScenePersistenceManager.SaveScene(LoadedNodeScenes.get(name));
        return true;
    }

    public void Update()
    {
        if(CurrentScene == null) return;
        UpdateManagers();
        UpdateEvents();
        RunNextAction();
    }

    private void UpdateManagers()
    {
        KeybindingInputManager.Update();
    }

    private void UpdateEvents()
    {
        Node queryResult = CurrentScene.NodeExistsAt(PlayerPosHelper.ToVector3i());

        // JUST ENTERED NODE
        if(queryResult != null && !queryResult.equals(CurrentNode))
        {
            Log.Event("EnterNode", queryResult.GetPosition().ToString());
        }

        // INSIDE SAME NODE
        if(queryResult != null && queryResult.equals(CurrentNode))
        {
            Log.Event("InsideNode", CurrentNode.GetPosition().ToString());
            CurrentNode.IncrementContactTime();
        }

        // EXITED NODE
        if(queryResult == null && CurrentNode != null)
        {
            Log.Event("ExitNode", CurrentNode.GetPosition().ToString());
            CurrentNode.ResetContactTime();
        }

        // FIRST CONTACT
        if(CurrentNode != null && CurrentNode.Contact())
        {
            Log.Event("Contact", CurrentNode.GetPosition().ToString());
            ActionQueue.addAll(CurrentNode.GetActions());
        }

        CurrentNode = queryResult;
    }

    public boolean AddNode(Vector3i position)
    {
        if(CurrentScene.NodeExistsAt(position) == null)
        {
            CurrentScene.AddNode(new Node(position));
            return true;
        }
        else
        {
            return false;
        }
    }

    public boolean AddNodeAction(Vector3i position, String actionType, String[] args)
    {
        Node affectedNode = CurrentScene.NodeExistsAt(position);

        switch (actionType)
        {
            case "presskey":
                return affectedNode.AddAction(new KeyInputAction(args[0], KeyInputAction.ActionType.PRESS));
            case "releasekey":
                return affectedNode.AddAction(new KeyInputAction(args[0], KeyInputAction.ActionType.RELEASE));
            case "pressmouse":
                return affectedNode.AddAction(new MouseInputAction(args[0], MouseInputAction.ActionType.PRESS));
            case "releasemouse":
                return affectedNode.AddAction(new MouseInputAction(args[0], MouseInputAction.ActionType.RELEASE));
            case "holdkeybinding":
                return affectedNode.AddAction(new KeybindingInputAction(args[0], KeybindingInputAction.ActionType.HOLD));
            case "releasekeybinding":
                return affectedNode.AddAction(new KeybindingInputAction(args[0], KeybindingInputAction.ActionType.RELEASE));
            default:
                return false;
        }
    }

    private void RunNextAction()
    {
        if(ActionQueue.isEmpty()) return;

        ActionQueue.getFirst().ExecuteAction();

        Log.Action(ActionQueue.getFirst().ToString());

        ActionQueue.removeFirst();
    }

    public Node NodeExistsAt(Vector3i position)
    {
        return CurrentScene.NodeExistsAt(position);
    }
}

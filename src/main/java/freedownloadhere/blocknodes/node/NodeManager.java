package freedownloadhere.blocknodes.node;

import freedownloadhere.blocknodes.command.AddNodeActionCommand;
import freedownloadhere.blocknodes.command.AddNodeCommand;
import freedownloadhere.blocknodes.command.AddSceneCommand;
import freedownloadhere.blocknodes.command.LoadSceneCommand;
import freedownloadhere.blocknodes.node.action.KeyInputAction;
import freedownloadhere.blocknodes.node.action.MouseInputAction;
import freedownloadhere.blocknodes.node.action.NodeAction;
import freedownloadhere.blocknodes.utils.Log;
import freedownloadhere.blocknodes.utils.PlayerPosHelper;
import freedownloadhere.blocknodes.utils.Vector3i;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import java.util.*;

public class NodeManager implements Observer
{
    private static NodeManager Instance;
    private final HashMap<String, NodeScene> LoadedNodeScenes;
    private NodeScene CurrentScene;
    private Node CurrentNode;
    private LinkedList<NodeAction> ActionQueue;

    public static void Instantiate()
    {
        Instance = new NodeManager();
        NodeAction.Instantiate();
        KeyInputAction.Instantiate();
        MouseInputAction.Instantiate();

        ClientCommandHandler.instance.registerCommand(new AddSceneCommand());
        ClientCommandHandler.instance.registerCommand(new LoadSceneCommand());
        ClientCommandHandler.instance.registerCommand(new AddNodeCommand());
        ClientCommandHandler.instance.registerCommand(new AddNodeActionCommand());
        MinecraftForge.EVENT_BUS.register(Instance);
    }
    public static NodeManager GetInstance()
    {
        return Instance;
    }
    private NodeManager()
    {
        LoadedNodeScenes = new HashMap<String, NodeScene>();
        ActionQueue = new LinkedList<NodeAction>();
    }

    public boolean AddEmptyScene(String name)
    {
        if(!LoadedNodeScenes.containsKey(name))
        {
            LoadedNodeScenes.put(name, new NodeScene(name));
            return true;
        }
        else
        {
            return false;
        }
    }
    public boolean LoadScene(String name)
    {
        if(LoadedNodeScenes.containsKey(name))
        {
            CurrentScene = LoadedNodeScenes.get(name);
            return true;
        }
        else
        {
            return false;
        }
    }

    @SubscribeEvent
    public void UpdateLoop(TickEvent.ClientTickEvent e)
    {
        if(Minecraft.getMinecraft().theWorld == null || Minecraft.getMinecraft().thePlayer == null) return;
        if(CurrentScene == null) return;
        if(e.phase != TickEvent.Phase.END) return;

        Node queryResult = CurrentScene.NodeExistsAt(PlayerPosHelper.ToVector3i());

        // JUST ENTERED NODE
        if(queryResult != null && !queryResult.equals(CurrentNode))
        {
            Log.Message("EnterNode", queryResult.GetPosition().ToString());
        }

        // INSIDE SAME NODE
        if(queryResult != null && queryResult.equals(CurrentNode))
        {
            Log.Message("InsideNode", CurrentNode.GetPosition().ToString());
            CurrentNode.IncrementContactTime();
        }

        // EXITED NODE
        if(queryResult == null && CurrentNode != null)
        {
            Log.Message("ExitNode", CurrentNode.GetPosition().ToString());
            CurrentNode.ResetContactTime();
        }

        // FIRST CONTACT
        if(CurrentNode != null && CurrentNode.Contact())
        {
            Log.Message("Contact", CurrentNode.GetPosition().ToString());
            ActionQueue.addAll(CurrentNode.GetActions());
        }

        RunNextAction();

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

        if(actionType.equals("presskey"))
            return affectedNode.AddAction(new KeyInputAction(args[0], KeyInputAction.ActionType.PRESS));
        else if(actionType.equals("releasekey"))
            return affectedNode.AddAction(new KeyInputAction(args[0], KeyInputAction.ActionType.RELEASE));
        else if(actionType.equals("pressmouse"))
            return affectedNode.AddAction(new MouseInputAction(args[0], MouseInputAction.ActionType.PRESS));
        else if(actionType.equals("releasemouse"))
            return affectedNode.AddAction(new MouseInputAction(args[0], MouseInputAction.ActionType.RELEASE));
        return false;
    }

    private void RunNextAction()
    {
        if(ActionQueue.isEmpty()) return;

        ActionQueue.getFirst().ExecuteAction();

        Log.Message("ActionQueue", "Executed next action in queue");

        ActionQueue.removeFirst();

    }

    public Node NodeExistsAt(Vector3i position)
    {
        return CurrentScene.NodeExistsAt(position);
    }

    @Override
    public void update(Observable o, Object arg)
    {

    }
}

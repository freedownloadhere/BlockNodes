package freedownloadhere.blocknodes.node.action;

import freedownloadhere.blocknodes.managers.KeybindingInputManager;

public class KeybindingInputAction extends NodeAction
{
    public enum ActionType
    {
        HOLD("Hold Keybinding"),
        RELEASE("Release Keybinding");

        String Name;
        ActionType(String name)
        {
            Name = name;
        }
    }

    private ActionType Type;
    private String KeybindingName;

    public KeybindingInputAction(String keybindingName, ActionType type)
    {
        Type = type;
        KeybindingName = keybindingName;
    }
    private KeybindingInputAction() { }

    @Override
    public void ExecuteAction()
    {
        if(Type == ActionType.HOLD)
            KeybindingInputManager.BeginHolding(KeybindingName);
        else if(Type == ActionType.RELEASE)
            KeybindingInputManager.Release(KeybindingName);
    }

    @Override
    public String ToString()
    {
        return Type.Name + ": " + KeybindingName;
    }
}

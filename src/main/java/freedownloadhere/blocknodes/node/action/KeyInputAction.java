package freedownloadhere.blocknodes.node.action;

import java.awt.event.KeyEvent;
import java.util.HashMap;

public class KeyInputAction extends NodeAction
{
    public static HashMap<String, Integer> KeycodeMap;

    public enum ActionType
    {
        PRESS,
        RELEASE;
    }
    private ActionType Type;
    private int Key;

    public static void Instantiate()
    {
        KeycodeMap = new HashMap<String, Integer>();

        for(char key = KeyEvent.VK_A; key <= KeyEvent.VK_Z; key++)
            KeycodeMap.put("" + key, (int)key);
    }
    public KeyInputAction(int key, ActionType type)
    {
        Key = key;
        Type = type;
    }
    public KeyInputAction(String name, ActionType type)
    {
        Key = KeycodeMap.get(name);
        Type = type;
    }
    private KeyInputAction() { }

    @Override
    public void ExecuteAction()
    {
        if(Type == ActionType.PRESS)
            InputHandler.keyPress(Key);
        else
            InputHandler.keyRelease(Key);
    }
}

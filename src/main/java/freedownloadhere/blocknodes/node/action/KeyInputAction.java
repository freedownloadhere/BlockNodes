package freedownloadhere.blocknodes.node.action;

import com.google.gson.annotations.SerializedName;

import java.awt.event.KeyEvent;
import java.util.HashMap;

public class KeyInputAction extends NodeAction
{
    public static HashMap<String, Integer> StringToKeycode;
    public static HashMap<Integer, String> KeycodeToString;

    public enum ActionType
    {
        PRESS("Press Key"),
        RELEASE("Release Key");

        String Name;
        ActionType(String name)
        {
            Name = name;
        }
    }

    //@SerializedName("type")
    private ActionType Type;
    //@SerializedName("key")
    private int Key;
    private String KeyName;

    public static void Instantiate()
    {
        StringToKeycode = new HashMap<String, Integer>();
        KeycodeToString = new HashMap<Integer, String>();

        for(char key = KeyEvent.VK_A; key <= KeyEvent.VK_Z; key++)
        {
            StringToKeycode.put("" + key, (int)key);
            KeycodeToString.put((int)key, "" + key);
        }
    }

    public KeyInputAction(int key, ActionType type)
    {
        Type = type;
        Key = key;
        KeyName = KeycodeToString.get(key);
    }
    public KeyInputAction(String name, ActionType type)
    {
        Type = type;
        Key = StringToKeycode.get(name);
        KeyName = name;
    }
    private KeyInputAction() { }

    @Override
    public void ExecuteAction()
    {
        InputHandler.keyPress(Key);
        if(Type == ActionType.RELEASE)
            InputHandler.keyRelease(Key);
    }

    @Override
    public String ToString()
    {
        return Type.Name + " " + KeyName;
    }
}

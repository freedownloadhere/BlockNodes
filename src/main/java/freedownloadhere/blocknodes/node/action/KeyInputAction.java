package freedownloadhere.blocknodes.node.action;

import com.google.gson.annotations.SerializedName;
import net.minecraft.client.settings.KeyBinding;

import javax.swing.text.JTextComponent;
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

    private ActionType Type;
    private int Key;
    private String KeyName;

    public static void Initialize()
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
        KeyBinding.setKeyBindState(Key, true);
        if(Type == ActionType.RELEASE)
            KeyBinding.setKeyBindState(Key, false);

        //InputHandler.keyPress(Key);
        //if(Type == ActionType.RELEASE)
            //InputHandler.keyRelease(Key);
    }

    @Override
    public String ToString()
    {
        return Type.Name + " " + KeyName;
    }
}

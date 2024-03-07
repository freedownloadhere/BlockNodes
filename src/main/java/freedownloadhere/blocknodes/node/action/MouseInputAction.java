package freedownloadhere.blocknodes.node.action;

import com.google.gson.annotations.SerializedName;

import java.awt.event.InputEvent;
import java.util.HashMap;

public class MouseInputAction extends NodeAction
{
    public static HashMap<String, Integer> StringToButton;
    public static HashMap<Integer, String> ButtonToString;

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
    private int Button;
    private String ButtonName;

    public static void Initialize()
    {
        StringToButton = new HashMap<String, Integer>();
        ButtonToString = new HashMap<Integer, String>();

        StringToButton.put("LMB", InputEvent.BUTTON1_MASK);
        StringToButton.put("RMB", InputEvent.BUTTON2_MASK);
        ButtonToString.put(InputEvent.BUTTON1_MASK, "LMB");
        ButtonToString.put(InputEvent.BUTTON2_MASK, "RMB");
    }

    public MouseInputAction(int button, ActionType type)
    {
        Type = type;
        Button = button;
        ButtonName = ButtonToString.get(button);
    }
    public MouseInputAction(String name, ActionType type)
    {
        Type = type;
        Button = StringToButton.get(name);
        ButtonName = name;
    }
    private MouseInputAction() { }

    @Override
    public void ExecuteAction()
    {
        if(Type == ActionType.PRESS)
            InputHandler.mousePress(Button);
        else
            InputHandler.mouseRelease(Button);
    }

    @Override
    public String ToString()
    {
        return Type.Name + " " + ButtonName;
    }
}

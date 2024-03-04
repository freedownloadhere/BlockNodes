package freedownloadhere.blocknodes.node.action;

import java.awt.*;
import java.awt.event.InputEvent;
import java.util.HashMap;

public class MouseInputAction extends NodeAction
{
    public static HashMap<String, Integer> MouseMap;

    public enum ActionType
    {
        PRESS,
        RELEASE;
    }
    private int Button;
    private ActionType Type;

    public static void Instantiate()
    {
        MouseMap = new HashMap<String, Integer>();
        MouseMap.put("LMB", InputEvent.BUTTON1_MASK);
        MouseMap.put("RMB", InputEvent.BUTTON2_MASK);
    }

    public MouseInputAction(int button, ActionType type)
    {
        Button = button;
        Type = type;
    }
    public MouseInputAction(String name, ActionType type)
    {
        Button = MouseMap.get(name);
        Type = type;
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
}

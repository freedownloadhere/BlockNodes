package freedownloadhere.blocknodes.node.action;

import com.google.gson.annotations.SerializedName;

import java.awt.*;

public class NodeAction
{
    public static Robot InputHandler;

    public static void Instantiate()
    {
        try { InputHandler = new Robot(); }
        catch(Exception e) { System.out.println(e.getCause().getMessage()); }
    }

    public void ExecuteAction() { }

    public String ToString() { return "Empty Action"; }
}

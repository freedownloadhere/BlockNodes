package freedownloadhere.blocknodes.node.action;

import freedownloadhere.blocknodes.utils.Log;

import java.awt.*;

public class NodeAction
{
    static Robot InputHandler;
    public static void Instantiate()
    {
        try { InputHandler = new Robot(); }
        catch(Exception e) { System.out.println(e.getCause().getMessage()); }
    }

    public NodeAction() { }

    public void ExecuteAction() { }

    // public abstract String ToString();
}

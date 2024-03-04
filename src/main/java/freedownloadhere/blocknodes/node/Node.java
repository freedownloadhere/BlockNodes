package freedownloadhere.blocknodes.node;

import freedownloadhere.blocknodes.node.action.NodeAction;
import freedownloadhere.blocknodes.utils.Log;
import freedownloadhere.blocknodes.utils.Vector3i;

import java.util.Observable;
import java.util.Vector;

public class Node extends Observable
{
    private boolean RequiresTriggerTime = false;
    private int TriggerTimeTicks = 5;
    private int CurrentContactTime = 0;

    // Expect Euclidean distance from player center to node center.
    // Also expect to be within regular node boundaries when analyzing proximity.
    private boolean RequiresCenterProximity = false;
    private double ProximityToTrigger = 0.25;

    private Vector3i Position;
    private Vector<NodeAction> Actions;
    private int ActionIndicator = 0;

    public Node()
    {
        Position = new Vector3i();
        Actions = new Vector<NodeAction>();
    }
    public Node(Vector3i position)
    {
        Position = position;
        Actions = new Vector<NodeAction>();
    }
    public Node(Vector3i position, Vector<NodeAction> actions)
    {
        Position = position;
        Actions = actions;
    }

    public void IncrementContactTime()
    {
        CurrentContactTime++;
        if(CurrentContactTime == TriggerTimeTicks)
            notifyObservers("JustContacted");
    }

    public void ResetContactTime()
    {
        CurrentContactTime = 0;
    }

    public boolean Contact()
    {
        return !RequiresTriggerTime || CurrentContactTime >= TriggerTimeTicks;
    }

    public boolean AddAction(NodeAction action)
    {
        Actions.add(action);
        return true;
    }

    public Vector<NodeAction> GetActions()
    {
        return Actions;
    }

    public Vector3i GetPosition()
    {
        return Position;
    }

    public int Hash()
    {
        return Position.Hash();
    }

    public boolean equals(Node other)
    {
        if(other == null) return false;
        return Position.equals(other.Position);
    }
}

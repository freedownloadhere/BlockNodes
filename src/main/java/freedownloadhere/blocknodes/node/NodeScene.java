package freedownloadhere.blocknodes.node;

import freedownloadhere.blocknodes.node.action.NodeAction;
import freedownloadhere.blocknodes.utils.Vector3i;

import java.util.HashMap;

public class NodeScene
{
    private HashMap<Integer, Node> Nodes;
    private String Name;

    public NodeScene(String name)
    {
        Nodes = new HashMap<Integer, Node>();
        Name = name;
    }

    public void AddNode(Node node)
    {
        Nodes.put(node.Hash(), node);
    }

    public void AddNodeAction(Vector3i position, NodeAction action)
    {
        Nodes.get(position.Hash()).AddAction(action);
    }

    public Node NodeExistsAt(Vector3i position)
    {
        if(!Nodes.containsKey(position.Hash())) return null;
        return Nodes.get(position.Hash());
    }
}

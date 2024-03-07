package freedownloadhere.blocknodes.command;

import freedownloadhere.blocknodes.managers.NodeManager;
import freedownloadhere.blocknodes.utils.Log;
import freedownloadhere.blocknodes.utils.PlayerPosHelper;
import freedownloadhere.blocknodes.utils.Vector3i;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;

public class AddNodeActionCommand extends CommandBase
{
    @Override
    public String getCommandName() {
        return "addaction";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return null;
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) throws CommandException
    {
        Vector3i position = PlayerPosHelper.StringToVector3i(args[0], args[1], args[2]);

        if(NodeManager.GetInstance().NodeExistsAt(position) == null)
            throw new CommandException("blocknodes.commandexception.nosuchnodeexists", (Object) args);

        if(!NodeManager.GetInstance().AddNodeAction(position, args[3], new String[]{args[4]}))
            throw new CommandException("blocknodes.commandexception.invalidactiontype", (Object) args);

        Log.Command("AddNode", "Added Action: \u00A7e" + args[3] + " " + args[4] + "\u00A7r to Node: \u00A7e" + position.ToString());
    }

    @Override
    public int getRequiredPermissionLevel() { return 0; }
}

package freedownloadhere.blocknodes.command;

import freedownloadhere.blocknodes.node.NodeManager;
import freedownloadhere.blocknodes.utils.Log;
import freedownloadhere.blocknodes.utils.PlayerPosHelper;
import freedownloadhere.blocknodes.utils.Vector3i;
import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;

public class AddNodeCommand extends CommandBase
{
    @Override
    public String getCommandName() {
        return "addnode";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return null;
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) throws CommandException {
        if(args.length != 3)
            throw new CommandException("blocknodes.commandexception.toomanyargs", (Object) args);

        Vector3i position = PlayerPosHelper.StringToVector3i(args[0], args[1], args[2]);

        if(!NodeManager.GetInstance().AddNode(position))
            throw new CommandException("blocknodes.commandexception.nodealreadyexists", (Object) args);

        Log.Message("AddNode","Added new empty node: " + position.ToString());
    }

    @Override
    public int getRequiredPermissionLevel() { return 0; }
}

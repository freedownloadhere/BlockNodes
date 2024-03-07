package freedownloadhere.blocknodes.command;

import freedownloadhere.blocknodes.managers.NodeManager;
import freedownloadhere.blocknodes.utils.Log;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;

public class AddSceneCommand extends CommandBase
{
    @Override
    public String getCommandName() {
        return "addscene";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return null;
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) throws CommandException
    {
        if(args.length != 1)
            throw new CommandException("blocknodes.commandexception.toomanyargs", (Object) args);

        if(!NodeManager.GetInstance().AddEmptyScene(args[0]))
            throw new CommandException("blocknodes.commandexception.scenealreadyexists", (Object) args);

        Log.Command("AddScene","Added new empty Scene: \u00A7e" + args[0]);
    }

    @Override
    public int getRequiredPermissionLevel() { return 0; }
}

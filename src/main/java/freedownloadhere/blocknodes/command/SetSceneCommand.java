package freedownloadhere.blocknodes.command;

import freedownloadhere.blocknodes.managers.NodeManager;
import freedownloadhere.blocknodes.utils.Log;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;

public class SetSceneCommand extends CommandBase
{
    @Override
    public String getCommandName() {
        return "setscene";
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

        if(!NodeManager.GetInstance().SetScene(args[0]))
            throw new CommandException("blocknodes.commandexception.nosuchsceneexists", (Object) args);

        Log.Command("SetScene", "Set the current Scene to: \u00A7e" + args[0]);
    }

    @Override
    public int getRequiredPermissionLevel() { return 0; }
}

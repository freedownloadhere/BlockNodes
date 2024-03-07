package freedownloadhere.blocknodes.command;

import freedownloadhere.blocknodes.managers.KeybindingInputManager;
import freedownloadhere.blocknodes.utils.Log;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;

public class KBIMCommand extends CommandBase
{
    @Override
    public String getCommandName() {
        return "kbim";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return null;
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) throws CommandException
    {
        if(args.length == 0)
            throw new CommandException("blocknodes.commandexception.notenoughargs", (Object) args);

        Log.Manager(KeybindingInputManager.ExecuteCommand(args));
    }

    @Override
    public int getRequiredPermissionLevel() { return 0; }
}

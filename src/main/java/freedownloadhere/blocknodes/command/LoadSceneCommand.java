package freedownloadhere.blocknodes.command;

import freedownloadhere.blocknodes.node.NodeManager;
import freedownloadhere.blocknodes.utils.Log;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;

public class LoadSceneCommand extends CommandBase
{
    @Override
    public String getCommandName() {
        return "loadscene";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return null;
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) throws CommandException
    {
        if(args.length != 1)
            throw new CommandException("blocknodes.loadscene.commandexception.toomanyargs", (Object) args);

        if(!NodeManager.GetInstance().LoadScene(args[0]))
            throw new CommandException("blocknodes.loadscene.commandexception.nosuchsceneexists", (Object) args);

        Log.Message("LoadScene", "Loaded scene with name " + args[0]);
    }

    @Override
    public int getRequiredPermissionLevel() { return 0; }
}

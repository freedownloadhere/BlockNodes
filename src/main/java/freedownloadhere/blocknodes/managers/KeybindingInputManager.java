package freedownloadhere.blocknodes.managers;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.command.CommandException;

import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class KeybindingInputManager
{
    private static final Set<KeyBinding> KeybindingsToManage = new TreeSet<KeyBinding>();
    private static HashMap<String, KeyBinding> KeybindingMap;

    public static void Initialize()
    {
        KeybindingMap = new HashMap<>();

        KeybindingMap.put("Forward", Minecraft.getMinecraft().gameSettings.keyBindForward);
        KeybindingMap.put("Left", Minecraft.getMinecraft().gameSettings.keyBindLeft);
        KeybindingMap.put("Back", Minecraft.getMinecraft().gameSettings.keyBindBack);
        KeybindingMap.put("Right", Minecraft.getMinecraft().gameSettings.keyBindRight);
        KeybindingMap.put("Jump", Minecraft.getMinecraft().gameSettings.keyBindJump);
        KeybindingMap.put("Sneak", Minecraft.getMinecraft().gameSettings.keyBindSneak);
        KeybindingMap.put("Sprint", Minecraft.getMinecraft().gameSettings.keyBindSprint);
        KeybindingMap.put("Inventory", Minecraft.getMinecraft().gameSettings.keyBindInventory);
        KeybindingMap.put("UseItem", Minecraft.getMinecraft().gameSettings.keyBindUseItem);
        KeybindingMap.put("Drop", Minecraft.getMinecraft().gameSettings.keyBindDrop);
        KeybindingMap.put("Attack", Minecraft.getMinecraft().gameSettings.keyBindAttack);
        KeybindingMap.put("PickBlock", Minecraft.getMinecraft().gameSettings.keyBindPickBlock);
        KeybindingMap.put("Chat", Minecraft.getMinecraft().gameSettings.keyBindChat);
        KeybindingMap.put("PlayerList", Minecraft.getMinecraft().gameSettings.keyBindPlayerList);
        KeybindingMap.put("Command", Minecraft.getMinecraft().gameSettings.keyBindCommand);
        KeybindingMap.put("Screenshot", Minecraft.getMinecraft().gameSettings.keyBindScreenshot);
        KeybindingMap.put("TogglePerspective", Minecraft.getMinecraft().gameSettings.keyBindTogglePerspective);

        for(int i = 0; i < 9; i++)
            KeybindingMap.put("Slot" + (i + 1), Minecraft.getMinecraft().gameSettings.keyBindsHotbar[i]);
    }

    public static void BeginHolding(KeyBinding kb)
    {
        KeybindingsToManage.add(kb);
    }

    public static void Release(KeyBinding kb)
    {
        KeyBinding.setKeyBindState(kb.getKeyCode(), false);
        KeybindingsToManage.remove(kb);
    }

    public static void BeginHolding(String kbName)
    {
        KeybindingsToManage.add(KeybindingMap.get(kbName));
    }

    public static void Release(String kbName)
    {
        KeyBinding.setKeyBindState(KeybindingMap.get(kbName).getKeyCode(), false);
        KeybindingsToManage.remove(KeybindingMap.get(kbName));
    }

    public static void Update()
    {
        for(KeyBinding kb : KeybindingsToManage)
            KeyBinding.setKeyBindState(kb.getKeyCode(), true);
    }

    public static String ExecuteCommand(String[] args)
            throws CommandException
    {
        String response;

        switch (args[0])
        {
            case "reset":
                for(KeyBinding kb : KeybindingsToManage) Release(kb);
                response = "Reset All Keybindings";
                break;
            case "hold":
                if(args.length != 2) throw new CommandException("blocknodes.commandexception.notenoughargs",  (Object) args);
                BeginHolding(args[1]);
                response = "Hold Keybinding: \u00A7e" + args[1];
                break;
            case "release":
                if(args.length != 2) throw new CommandException("blocknodes.commandexception.notenoughargs",  (Object) args);
                Release(args[1]);
                response = "Release Keybinding: \u00A7e" + args[1];
                break;
            default:
                if(args.length != 2) throw new CommandException("blocknodes.commandexception.invalidargs",  (Object) args);
                response = "Invalid!";
                break;
        }

        return response;
    }
}

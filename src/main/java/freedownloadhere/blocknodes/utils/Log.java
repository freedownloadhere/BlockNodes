package freedownloadhere.blocknodes.utils;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;

public class Log
{
    public static void Warning(String message)
    {
        Minecraft.getMinecraft().thePlayer.addChatComponentMessage(
                new ChatComponentText(
                        "\u00A7e\u00A7l[\u00A7eWarning\u00A7e\u00A7l]\u00A7r "
                                + message
                )
        );
    }

    public static void Error(String message)
    {
        Minecraft.getMinecraft().thePlayer.addChatComponentMessage(
                new ChatComponentText(
                        "\u00A7c\u00A7l[\u00A7cError\u00A7c\u00A7l]\u00A7r "
                                + message
                )
        );
    }

    public static void Message(String prefix, String message)
    {
        Minecraft.getMinecraft().thePlayer.addChatComponentMessage(
                new ChatComponentText(
                        "\u00A77\u00A7l[\u00A77" + prefix + "\u00A77\u00A7l]\u00A7r "
                                + message
                )
        );
    }

    public static void Action(String message)
    {
        Minecraft.getMinecraft().thePlayer.addChatComponentMessage(
                new ChatComponentText(
                        "\u00A73\u00A7l[\u00A73Action\u00A73\u00A7l]\u00A7r "
                                + message
                )
        );
    }

    public static void Manager(String message)
    {
        Minecraft.getMinecraft().thePlayer.addChatComponentMessage(
                new ChatComponentText(
                        "\u00A76\u00A7l[\u00A76Manager\u00A76\u00A7l]\u00A7r "
                                + message
                )
        );
    }

    public static void Event(String eventName, String message)
    {
        Minecraft.getMinecraft().thePlayer.addChatComponentMessage(
                new ChatComponentText(
                        "\u00A75\u00A7l[\u00A75Event\u00A75\u00A7l]\u00A7r "
                        + "\u00A77\u00A7l[\u00A77" + eventName + "\u00A77\u00A7l]\u00A7r "
                        + message
                )
        );
    }

    public static void Command(String commandName, String message)
    {
        Minecraft.getMinecraft().thePlayer.addChatComponentMessage(
                new ChatComponentText(
                        "\u00A78\u00A7l[\u00A78Command\u00A78\u00A7l]\u00A7r "
                                + "\u00A77\u00A7l[\u00A77" + commandName + "\u00A77\u00A7l]\u00A7r "
                                + message
                )
        );
    }
}

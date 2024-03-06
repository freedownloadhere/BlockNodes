package freedownloadhere.blocknodes.utils;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;

public class Log
{
    public static void Error(String prefix, String message)
    {
        Minecraft.getMinecraft().thePlayer.addChatComponentMessage(
                new ChatComponentText(
                        "\u00A73\u00A7l[\u00A73" + prefix + "\u00A73\u00A7l]\u00A7r "
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
}

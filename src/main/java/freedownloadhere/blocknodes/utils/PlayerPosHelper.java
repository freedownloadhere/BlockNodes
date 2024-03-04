package freedownloadhere.blocknodes.utils;

import net.minecraft.client.Minecraft;
import net.minecraft.util.BlockPos;

public class PlayerPosHelper
{
    public static int GetIntX()
    {
        double pos = Minecraft.getMinecraft().thePlayer.posX;
        return (pos >= 0 ? (int)pos : (int)pos - 1);
    }

    public static int GetIntY()
    {
        double pos = Minecraft.getMinecraft().thePlayer.posY;
        return (pos >= 0 ? (int)pos : (int)pos - 1);
    }

    public static int GetIntZ()
    {
        double pos = Minecraft.getMinecraft().thePlayer.posZ;
        return (pos >= 0 ? (int)pos : (int)pos - 1);
    }

    public static Vector3i StringToVector3i(String x, String y, String z)
    {
        return new Vector3i(
                x.equals("~") ? PlayerPosHelper.GetIntX() : Integer.parseInt(x),
                y.equals("~") ? PlayerPosHelper.GetIntY() : Integer.parseInt(y),
                z.equals("~") ? PlayerPosHelper.GetIntZ() : Integer.parseInt(z)
        );
    }

    public static Vector3i ToVector3i()
    {
        return new Vector3i(
                GetIntX(),
                GetIntY(),
                GetIntZ()
        );
    }
}

package freedownloadhere.blocknodes;

import freedownloadhere.blocknodes.managers.NodeManager;
import net.minecraft.client.Minecraft;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class Root
{
    private static Root Instance;
    private Root() { }

    public static void Begin()
    {
        Instance = new Root();
        MinecraftForge.EVENT_BUS.register(Instance);
        NodeManager.Instantiate();
    }

    @SubscribeEvent
    public void Update(TickEvent.ClientTickEvent e)
    {
        if(Minecraft.getMinecraft().theWorld == null || Minecraft.getMinecraft().thePlayer == null) return;
        if(e.phase != TickEvent.Phase.END) return;

        NodeManager.GetInstance().Update();
    }
}

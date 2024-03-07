package freedownloadhere.blocknodes;

import freedownloadhere.blocknodes.managers.NodeManager;
import freedownloadhere.blocknodes.utils.References;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = References.MOD_ID, name = References.MOD_NAME, version = References.MOD_VERSION)
public class TheMod
{
    @Mod.EventHandler
    public void PreInit(FMLPreInitializationEvent e) { }

    @Mod.EventHandler
    public void Init(FMLInitializationEvent e)
    {
        Root.Begin();
    }

    @Mod.EventHandler
    public void PostInit(FMLPostInitializationEvent e) { }
}

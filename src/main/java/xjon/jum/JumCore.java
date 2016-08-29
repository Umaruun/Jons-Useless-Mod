package xjon.jum;

import java.io.File;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import xjon.jum.event.ConfigEvents;
import xjon.jum.event.FOVEvents;
import xjon.jum.event.JoinWorldEvents;
import xjon.jum.event.PlayerEvents;
import xjon.jum.init.UselessAchievements;
import xjon.jum.init.UselessBiomes;
import xjon.jum.init.UselessBlocks;
import xjon.jum.init.UselessDimensions;
import xjon.jum.init.UselessEntities;
import xjon.jum.init.UselessItems;
import xjon.jum.init.UselessRecipes;
import xjon.jum.init.UselessSounds;
import xjon.jum.init.UselessTileEntities;
import xjon.jum.network.NetworkHandler;
import xjon.jum.proxy.CommonProxy;
import xjon.jum.util.Reference;
import xjon.jum.util.UselessConfiguration;
import xjon.jum.util.UselessTab;
import xjon.jum.world.gen.UselessGeneration;

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.VERSION, guiFactory = Reference.GUI_FACTORY)
public class JumCore {

	@SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.SERVER_PROXY_CLASS)
	public static CommonProxy proxy;
	
	public static final UselessTab tabJUM = new UselessTab("tabJUM");
	
	public static Configuration config;
		
	@Instance(Reference.MOD_ID)
	public static JumCore instance;
	
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		config = new Configuration(new File("config/JonsUselessMod.cfg"));
		config.load();
		UselessConfiguration.syncConfig();
		UselessItems.checks();
		UselessItems.init();
		UselessItems.registerItems();
		UselessBlocks.init();
		UselessBlocks.register();
		UselessItems.registerItemBlocks();
		UselessEntities.init();
		UselessBiomes.init();
		UselessDimensions.init();
		UselessSounds.init();
		//NetworkHandler.init();
		MinecraftForge.EVENT_BUS.register(new PlayerEvents());
		MinecraftForge.EVENT_BUS.register(new JoinWorldEvents());
		MinecraftForge.EVENT_BUS.register(new FOVEvents());
		proxy.registerRenders();
	}
	
	@EventHandler
	public void Init(FMLInitializationEvent event)
	{
		UselessAchievements.loadAchievements();
		UselessRecipes.register();
		UselessTileEntities.register();
		GameRegistry.registerWorldGenerator(new UselessGeneration(), 0);
		MinecraftForge.EVENT_BUS.register(JumCore.instance);
		MinecraftForge.EVENT_BUS.register(new ConfigEvents());
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{

	}
	
}

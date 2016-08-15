package xjon.jum.event;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.ItemCraftedEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.ItemPickupEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedOutEvent;
import xjon.jum.blocks.UselessMachine;
import xjon.jum.init.UselessAchievements;
import xjon.jum.init.UselessItems;
import xjon.jum.util.Log;
import xjon.jum.util.UselessConfiguration;
import xjon.jum.world.dimension.TeleporterUseless;

public class PlayerEvents{

	@SubscribeEvent
	public void pickup(ItemPickupEvent event)
	{
		if (event.pickedUp.getEntityItem().getItem().equals(UselessItems.useless_material))
		    {
			event.player.addStat(UselessAchievements.uselessMining, 1);
			}
	}
	
	@SubscribeEvent
	public void crafting(ItemCraftedEvent event)
	{
		if (event.crafting.getItem().equals(UselessItems.useless_bro))
		{
			event.player.addStat(UselessAchievements.uselessBro, 1);
		}
		
		if (event.crafting.getItem().equals(UselessItems.useless_multitool))
		{
			event.player.addStat(UselessAchievements.uselessMultitool, 1);
		}
	
	}
	
	@SubscribeEvent
	public void loggedOut(PlayerLoggedOutEvent event)
	{
		if (event.player.dimension == UselessConfiguration.uselessDimensionId && event.player instanceof EntityPlayerMP)
		{
				EntityPlayerMP playerMP = (EntityPlayerMP)event.player;
				
				playerMP.mcServer.getConfigurationManager().transferPlayerToDimension(playerMP, 0, new TeleporterUseless(playerMP.mcServer.worldServerForDimension(0)));
				event.player.setPositionAndUpdate(UselessMachine.x1, UselessMachine.y1, UselessMachine.z1);
				Log.warn("Player teleported back to the overworld");
		}
	}
		
}

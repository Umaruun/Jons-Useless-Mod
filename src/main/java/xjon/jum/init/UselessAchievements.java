package xjon.jum.init;

import net.minecraft.init.Blocks;
import net.minecraft.stats.Achievement;
import net.minecraftforge.common.AchievementPage;

public class UselessAchievements {

	public static Achievement uselessMining;
	public static Achievement uselessBro;
	public static Achievement uselessMachine;
	public static Achievement uselessMultitool;
	
	public static AchievementPage page;
	
	public static void loadAchievements()
	{
		uselessMining = new Achievement("achievement.jum_mining", "UselessMining", 0, 0, UselessItems.useless_material, null);
		uselessBro = new Achievement("achievement.jum_bro", "UselessBro", 2, 1, UselessItems.useless_bro, uselessMining);
		uselessMachine = new Achievement("achievement.jum_machine", "UselessMachine", 3, -1, UselessBlocks.useless_machine, uselessMining);
		uselessMultitool = new Achievement("achievement.jum_multitool", "UselessMultitool", -3, -2, UselessItems.useless_multitool, uselessMachine);
		
		page = new AchievementPage("Jon's Useless Mod", uselessMining, uselessBro, uselessMachine, uselessMultitool);
	}
	
	public static void registerPage()
	{
		AchievementPage.registerAchievementPage(page);
	}
	
}

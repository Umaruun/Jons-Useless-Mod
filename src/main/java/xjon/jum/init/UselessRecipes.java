package xjon.jum.init;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class UselessRecipes {

	public static void register()
	{
		GameRegistry.addRecipe(new ItemStack(UselessItems.useless_sword), new Object[]{"U","U","S", 'U', UselessItems.useless_material, 'S', Items.stick});
		GameRegistry.addRecipe(new ItemStack(UselessItems.useless_axe), new Object[]{"UU","US"," S", 'U', UselessItems.useless_material, 'S', Items.stick});
		GameRegistry.addRecipe(new ItemStack(UselessItems.useless_hoe), new Object[]{"UU"," S"," S", 'U', UselessItems.useless_material, 'S', Items.stick});
		GameRegistry.addRecipe(new ItemStack(UselessItems.useless_bro), new Object[]{"H",'H', UselessItems.useless_hoe});
		GameRegistry.addRecipe(new ItemStack(UselessItems.useless_pickaxe), new Object[]{"UUU"," S "," S ", 'U', UselessItems.useless_material, 'S', Items.stick});
		GameRegistry.addRecipe(new ItemStack(UselessItems.useless_shovel), new Object[]{"U","S","S", 'U', UselessItems.useless_material, 'S', Items.stick});
		GameRegistry.addRecipe(new ItemStack(UselessItems.useless_helmet), new Object[]{"UUU","U U", 'U', UselessItems.useless_material});
		GameRegistry.addRecipe(new ItemStack(UselessItems.useless_chestplate), new Object[]{"U U","UUU","UUU", 'U', UselessItems.useless_material});
		GameRegistry.addRecipe(new ItemStack(UselessItems.useless_leggings), new Object[]{"UUU","U U","U U", 'U', UselessItems.useless_material});
		GameRegistry.addRecipe(new ItemStack(UselessItems.useless_boots), new Object[]{"U U","U U", 'U', UselessItems.useless_material});
		GameRegistry.addRecipe(new ItemStack(UselessItems.useless_bow), new Object[]{" US", "U S", " US", 'U', UselessItems.useless_material, 'S', Items.string});
		GameRegistry.addRecipe(new ItemStack(UselessItems.useless_arrow, 4), new Object[]{"T", "U", "F", 'T', Items.flint, 'U', UselessItems.useless_material, 'F', Items.feather});
		GameRegistry.addRecipe(new ItemStack(UselessItems.useless_multitool), new Object[]{"ASP","B$B", "DHD", 'P', UselessItems.useless_pickaxe, '$', UselessItems.useless_shovel, 'A', UselessItems.useless_axe, 'B', UselessBlocks.super_useless_block, 'S', UselessItems.useless_sword, 'D', Blocks.diamond_block, 'H', UselessItems.useless_hoe});
		GameRegistry.addRecipe(new ItemStack(UselessBlocks.useless_block), new Object[]{"UUU","UUU","UUU", 'U', UselessItems.useless_material});
		GameRegistry.addRecipe(new ItemStack(UselessBlocks.super_useless_block), new Object[]{"UUU","UUU","UUU", 'U', UselessItems.super_useless_material});
		GameRegistry.addRecipe(new ItemStack(UselessBlocks.useless_chest), new Object[]{"UUU","U U", "UUU", 'U', UselessItems.useless_material});
		GameRegistry.addRecipe(new ItemStack(UselessBlocks.useless_machine), new Object[]{"QGQ","DRD", "ULU", 'Q', Blocks.quartz_block, 'L', Blocks.sea_lantern, 'D', Items.diamond, 'R', Blocks.redstone_block, 'U', UselessBlocks.useless_block, 'G', Items.glowstone_dust});
		
		
		GameRegistry.addShapelessRecipe(new ItemStack(UselessItems.useless_material, 9), new ItemStack(UselessBlocks.useless_block));
		GameRegistry.addShapelessRecipe(new ItemStack(UselessItems.super_useless_material, 9), new ItemStack(UselessBlocks.super_useless_block));
		
		GameRegistry.addSmelting(UselessItems.useless_material, new ItemStack(UselessItems.useless_food), 3.0F);
		GameRegistry.addSmelting(UselessBlocks.useless_ore, new ItemStack(UselessItems.useless_material), 5.0F);
		GameRegistry.addSmelting(UselessBlocks.better_useless_ore, new ItemStack(UselessItems.super_useless_material), 7.0F);
		GameRegistry.addSmelting(UselessBlocks.legitimate_diamond_ore, new ItemStack(UselessItems.legitimate_diamond), 1.0F);
	}
	
}

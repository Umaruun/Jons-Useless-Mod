package xjon.jum.items;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemBlockUselessOre extends ItemBlock {

	public ItemBlockUselessOre(Block block) {
		super(block);
	}
	
	@SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer playerIn, List toolTip, boolean advanced)
    {
		stack.setStackDisplayName(EnumChatFormatting.WHITE + "Useless Ore");
    	toolTip.add("Generates in the Overworld");
    }

}

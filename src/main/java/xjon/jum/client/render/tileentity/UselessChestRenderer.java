package xjon.jum.client.render.tileentity;

import net.minecraft.block.Block;
import net.minecraft.client.model.ModelChest;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import xjon.jum.blocks.UselessChest;
import xjon.jum.tileentity.TileEntityUselessChest;

@SideOnly(Side.CLIENT)
public class UselessChestRenderer extends TileEntitySpecialRenderer<TileEntityUselessChest>
{
    private static final ResourceLocation textureNormal = new ResourceLocation("jum:textures/entity/chest/normal.png");
    private ModelChest simpleChest = new ModelChest();
	private RenderManager renderManager;

    public UselessChestRenderer(RenderManager renderManager) {
		this.renderManager = renderManager;
	}

	public void func_180538_a(TileEntityUselessChest p_180538_1_, double p_180538_2_, double p_180538_4_, double p_180538_6_, float p_180538_8_, int p_180538_9_)
    {
        int j;

        if (!p_180538_1_.hasWorldObj())
        {
            j = 0;
        }
        else
        {
            Block block = p_180538_1_.getBlockType();
            j = p_180538_1_.getBlockMetadata();

            if (block instanceof UselessChest && j == 0)
            {
                ((UselessChest)block).checkForSurroundingChests(p_180538_1_.getWorld(), p_180538_1_.getPos(), p_180538_1_.getWorld().getBlockState(p_180538_1_.getPos()));
                j = p_180538_1_.getBlockMetadata();
            }
        }

        if (p_180538_1_.adjacentChestZNeg == null && p_180538_1_.adjacentChestXNeg == null)
        {
            ModelChest modelchest;

            if (p_180538_1_.adjacentChestXPos == null && p_180538_1_.adjacentChestZPos == null)
            {
                modelchest = this.simpleChest;

                if (p_180538_9_ >= 0)
                {
                    this.bindTexture(DESTROY_STAGES[p_180538_9_]);
                    GlStateManager.matrixMode(5890);
                    GlStateManager.pushMatrix();
                    GlStateManager.scale(4.0F, 4.0F, 1.0F);
                    GlStateManager.translate(0.0625F, 0.0625F, 0.0625F);
                    GlStateManager.matrixMode(5888);
                }
                else
                {
                    this.bindTexture(textureNormal);
                }
            }
            else
            {
                modelchest = this.simpleChest;

                if (p_180538_9_ >= 0)
                {
                    this.bindTexture(DESTROY_STAGES[p_180538_9_]);
                    GlStateManager.matrixMode(5890);
                    GlStateManager.pushMatrix();
                    GlStateManager.scale(8.0F, 4.0F, 1.0F);
                    GlStateManager.translate(0.0625F, 0.0625F, 0.0625F);
                    GlStateManager.matrixMode(5888);
                }
        
            }

            GlStateManager.pushMatrix();
            GlStateManager.enableRescaleNormal();

            if (p_180538_9_ < 0)
            {
                GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
            }

            GlStateManager.translate((float)p_180538_2_, (float)p_180538_4_ + 1.0F, (float)p_180538_6_ + 1.0F);
            GlStateManager.scale(1.0F, -1.0F, -1.0F);
            GlStateManager.translate(0.5F, 0.5F, 0.5F);
            short short1 = 0;

            if (j == 2)
            {
                short1 = 180;
            }

            if (j == 3)
            {
                short1 = 0;
            }

            if (j == 4)
            {
                short1 = 90;
            }

            if (j == 5)
            {
                short1 = -90;
            }

            if (j == 2 && p_180538_1_.adjacentChestXPos != null)
            {
                GlStateManager.translate(1.0F, 0.0F, 0.0F);
            }

            if (j == 5 && p_180538_1_.adjacentChestZPos != null)
            {
                GlStateManager.translate(0.0F, 0.0F, -1.0F);
            }

            GlStateManager.rotate((float)short1, 0.0F, 1.0F, 0.0F);
            GlStateManager.translate(-0.5F, -0.5F, -0.5F);
            float f1 = p_180538_1_.prevLidAngle + (p_180538_1_.lidAngle - p_180538_1_.prevLidAngle) * p_180538_8_;
            float f2;

            if (p_180538_1_.adjacentChestZNeg != null)
            {
                f2 = p_180538_1_.adjacentChestZNeg.prevLidAngle + (p_180538_1_.adjacentChestZNeg.lidAngle - p_180538_1_.adjacentChestZNeg.prevLidAngle) * p_180538_8_;

                if (f2 > f1)
                {
                    f1 = f2;
                }
            }

            if (p_180538_1_.adjacentChestXNeg != null)
            {
                f2 = p_180538_1_.adjacentChestXNeg.prevLidAngle + (p_180538_1_.adjacentChestXNeg.lidAngle - p_180538_1_.adjacentChestXNeg.prevLidAngle) * p_180538_8_;

                if (f2 > f1)
                {
                    f1 = f2;
                }
            }

            f1 = 1.0F - f1;
            f1 = 1.0F - f1 * f1 * f1;
            modelchest.chestLid.rotateAngleX = -(f1 * (float)Math.PI / 2.0F);
            modelchest.renderAll();
            GlStateManager.disableRescaleNormal();
            GlStateManager.popMatrix();
            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);

            if (p_180538_9_ >= 0)
            {
                GlStateManager.matrixMode(5890);
                GlStateManager.popMatrix();
                GlStateManager.matrixMode(5888);
            }
        }
    }

	@Override
	public void renderTileEntityAt(TileEntityUselessChest te, double x, double y, double z, float partialTicks, int destroyStage)
	{
		this.func_180538_a(te, x, y, z, partialTicks, destroyStage);
	}
}
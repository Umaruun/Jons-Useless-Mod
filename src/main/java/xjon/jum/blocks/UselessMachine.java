package xjon.jum.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import xjon.jum.init.UselessAchievements;
import xjon.jum.init.UselessBlocks;
import xjon.jum.init.UselessDimensions;
import xjon.jum.util.Log;
import xjon.jum.util.Reference;
import xjon.jum.util.UselessConfiguration;
import xjon.jum.world.dimension.TeleporterUseless;

public class UselessMachine extends Block {
		
	public static final PropertyDirection FACING = BlockHorizontal.FACING;
	private boolean flag; 
	public static int x1, y1, z1;
	
	public UselessMachine(Material material) {
		super(material);
		setHardness(220.0F);
		setHarvestLevel("pickaxe", 2);
		this.setLightLevel(0.4F);
		this.setSoundType(SoundType.METAL);
		this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));
	}
	
	@Override
	public void onBlockClicked(World worldIn, BlockPos pos, EntityPlayer playerIn)
	{		
		EntityPlayerMP playerMP = (EntityPlayerMP) playerIn;
		
		if(!UselessConfiguration.isUseless)		
		{
			if(playerIn instanceof EntityPlayerMP)
			{
					if (worldIn.provider.getDimension() != UselessConfiguration.uselessDimensionId) {
						x1 = playerIn.getPosition().getX();
						y1 = playerIn.getPosition().getY() + 1;
						z1 = playerIn.getPosition().getZ();
						flag = true;
						playerMP.mcServer.getPlayerList().transferPlayerToDimension(playerMP, UselessConfiguration.uselessDimensionId, new TeleporterUseless(playerMP.mcServer.worldServerForDimension(UselessConfiguration.uselessDimensionId)));
						playerIn.setPositionAndUpdate(playerIn.posX, playerIn.posY + 1, playerIn.posZ);
						for (int x = -6; x <= 6; ++x)
						{ for (int y = -2; y <= 2; ++y)
							{ for (int z = -6; z <= 6; ++z)
								{
									if(playerIn.worldObj.getBlockState(new BlockPos(playerIn.posX + x, playerIn.posY + y, playerIn.posZ + z)).equals(UselessBlocks.useless_machine.getDefaultState()))
									{
										playerIn.setPositionAndUpdate(playerIn.posX + x, playerIn.posY + y + 1, playerIn.posZ + z + 1.5);
										break;
									}
								}
							}
						}
					} else if (flag) {
						playerMP.mcServer.getPlayerList().transferPlayerToDimension(playerMP, 0, new TeleporterUseless(playerMP.mcServer.worldServerForDimension(0)));
						playerIn.setPositionAndUpdate(x1, y1 + 1, z1);	
						flag = false;
					}
					  else
					  {
						  playerMP.mcServer.getPlayerList().transferPlayerToDimension(playerMP, 0, new TeleporterUseless(playerMP.mcServer.worldServerForDimension(0)));
						  playerIn.setPositionAndUpdate(worldIn.getSpawnPoint().getX(), worldIn.getSpawnPoint().getY(), worldIn.getSpawnPoint().getZ());
						  for (int y = playerIn.getPosition().getY(); y < 255; ++y)
						  {
							  if (worldIn.getBlockState(new BlockPos(playerIn.posX, y, playerIn.posZ)).equals(Blocks.AIR) && worldIn.getBlockState(new BlockPos(playerIn.posX, y + 1, playerIn.posZ)).equals(Blocks.AIR))
							  {
								  playerIn.setPositionAndUpdate(playerIn.posX, y + 2, playerIn.posZ);
								  break;
							  }
						  }
						  Log.error("No coordinates were found, teleporting player back to spawn point");
					  }
			}
		}
		else
		{
			SoundEvent sound = SoundEvent.REGISTRY.getObject(new ResourceLocation(Reference.MOD_ID + ":nope"));
			playerMP.worldObj.playSound(playerIn, pos, sound, SoundCategory.AMBIENT, 1.0F, 1.0F);
		}
	}
	
	@Override
	public TileEntity createTileEntity(World world, IBlockState state) {

		return super.createTileEntity(world, state);
	}
	
	@Override
	public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) {
	        this.setDefaultFacing(worldIn, pos, state);
	    }
	
	
	private void setDefaultFacing(World worldIn, BlockPos pos, IBlockState state)
    {
		if (!worldIn.isRemote)
        {
            IBlockState iblockstate = worldIn.getBlockState(pos.north());
            IBlockState iblockstate1 = worldIn.getBlockState(pos.south());
            IBlockState iblockstate2 = worldIn.getBlockState(pos.west());
            IBlockState iblockstate3 = worldIn.getBlockState(pos.east());
            EnumFacing enumfacing = state.getValue(FACING);

            if (enumfacing == EnumFacing.NORTH && iblockstate.isFullBlock() && !iblockstate1.isFullBlock())
            {
                enumfacing = EnumFacing.SOUTH;
            }
            else if (enumfacing == EnumFacing.SOUTH && iblockstate1.isFullBlock() && !iblockstate.isFullBlock())
            {
                enumfacing = EnumFacing.NORTH;
            }
            else if (enumfacing == EnumFacing.WEST && iblockstate2.isFullBlock() && !iblockstate3.isFullBlock())
            {
                enumfacing = EnumFacing.EAST;
            }
            else if (enumfacing == EnumFacing.EAST && iblockstate3.isFullBlock() && !iblockstate2.isFullBlock())
            {
                enumfacing = EnumFacing.WEST;
            }

            worldIn.setBlockState(pos, state.withProperty(FACING, enumfacing), 2);
        }
    }
	
	@Override
	public IBlockState onBlockPlaced(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
    {
		return this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing());
    }

	@Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack)
    {
        worldIn.setBlockState(pos, state.withProperty(FACING, placer.getHorizontalFacing()), 2);

        ((EntityPlayer)placer).addStat(UselessAchievements.uselessMachine, 1);
    }
    
    @Override
    public int getMetaFromState(IBlockState state)
    {
        return state.getValue(FACING).getIndex();
    }
    
    @Override
    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, FACING);
    }
    
    @Override
    public boolean isOpaqueCube(IBlockState state) {
    	return false;
    }
}
package xjon.jum.entity.mob;

import net.minecraft.block.Block;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIPanic;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAITempt;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import xjon.jum.init.UselessItems;
import xjon.jum.util.Reference;
import xjon.jum.util.UselessConfiguration;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class EntityUselessDave extends EntityMob {
	
	public static SoundEvent SND_HI = new SoundEvent(new ResourceLocation(Reference.MOD_ID, "hidave"));
	public static SoundEvent SND_HIT = new SoundEvent(new ResourceLocation(Reference.MOD_ID, "hitdave"));
    public static SoundEvent SND_DEATH = new SoundEvent(new ResourceLocation(Reference.MOD_ID, "deathdave"));
    
	public EntityUselessDave(World worldIn) {
		super(worldIn);
		this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(2, new EntityAIAttackMelee(this, 1.0D, false));
		this.tasks.addTask(3, new EntityAIPanic(this, 1.2D));
        this.tasks.addTask(4, new EntityAITempt(this, 5.0D, UselessItems.useless_food, false));
        this.tasks.addTask(5, new EntityAIMoveTowardsRestriction(this, 1.0D));
		this.tasks.addTask(8, new EntityAIWander(this, 1.0D));
        this.tasks.addTask(7, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(9, new EntityAILookIdle(this));

        
        this.applyEntityAI();
        this.setSize(0.6F, 1.95F);
    }

	
	protected void applyEntityAI()
    {
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget<>(this, EntityPlayer.class, true));
    }
	
	public boolean isAIEnabled()
	{
		return true;
	}
	
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		
		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(UselessConfiguration.daveHealth);
		this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(36.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.23000000417232513D);
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(5.0D);
	}
	
	@Override
	protected SoundEvent getAmbientSound()
    {
        return SND_HI;
    }
	
	@Override
	protected SoundEvent getHurtSound()
	{
		return SND_HIT;
	}
	
	@Override
	protected SoundEvent getDeathSound()
    {
		return SND_DEATH;
    }
	
	@Override
	protected void playHurtSound(DamageSource source) {
		playSound(SND_HIT, 0.75F, 1.0F);;
	}
	
	@Override
	public void playLivingSound() {
		playSound(SND_HI, 0.37F, 1.0F);;
	}
		
	protected void playStepSound(BlockPos p_180429_1_, Block p_180429_2_)
	{
        this.playSound(SoundEvents.ENTITY_ZOMBIE_STEP, 0.15F, 1.0F);
	}
	
	protected Item getDropItem()   
	{
		return UselessItems.useless_material;
	}
	
	@Override
	public boolean getCanSpawnHere()
    {
        return true;
    }
}

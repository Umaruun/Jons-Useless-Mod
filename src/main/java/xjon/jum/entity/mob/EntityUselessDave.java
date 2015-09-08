package xjon.jum.entity.mob;

import xjon.jum.init.UselessItems;
import net.minecraft.block.Block;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveThroughVillage;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIPanic;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAITempt;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class EntityUselessDave extends EntityMob {

	public EntityUselessDave(World worldIn) {
		super(worldIn);
		this.tasks.addTask(0, new EntityAIWander(this, 0.8D));
		this.tasks.addTask(1, new EntityAIPanic(this, 1.2D));
		this.tasks.addTask(2, new EntityAISwimming(this));
        this.tasks.addTask(3, new EntityAITempt(this, 5.0D, UselessItems.useless_food, false));
        this.tasks.addTask(4, new EntityAIAttackOnCollide(this, EntityPlayer.class, 1.0D, false));
        this.tasks.addTask(4, this.field_175455_a);
        
        this.applyEntityAI();
        this.setSize(0.6F, 1.95F);
    }
	
	protected void applyEntityAI()
    {
        this.targetTasks.addTask(4, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
    }
	
	public boolean isAIEnabled()
	{
		return true;
	}
	
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(36.0F);
		this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(35.0D);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.23000000417232513D);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(5.0D);
	}
	
	protected String getLivingSound()
    {
        return "mob.zombie.say";
    }
	
	protected String getHurtSound()
	{
	        return "mob.zombie.hurt";
	}
	
	protected String getDeathSound()
    {
        return "mob.zombie.death";
    }
	
	protected void playStepSound(BlockPos p_180429_1_, Block p_180429_2_)
	{
	        this.playSound("mob.zombie.step", 0.15F, 1.0F);
	}
	
	protected Item getDropItem()   
	{
	        return UselessItems.useless_material;
	}
	 
	 
	 
	 
	
	

}
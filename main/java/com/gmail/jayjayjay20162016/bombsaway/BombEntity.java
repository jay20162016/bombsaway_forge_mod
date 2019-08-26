package com.gmail.jayjayjay20162016.bombsaway;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ProjectileItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ItemParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class BombEntity extends ProjectileItemEntity {
	private int power;
	private float weight;
	private boolean nuclear;
	private boolean fire;

	public BombEntity(EntityType<? extends BombEntity> p_i50159_1_, World p_i50159_2_) {
		this(p_i50159_1_, p_i50159_2_, 0, 0, false, false);
	}

	public BombEntity(World worldIn, LivingEntity throwerIn) {
		this(worldIn, throwerIn, 0, 0, false, false);
	}

	public BombEntity(World worldIn, double x, double y, double z) {
		this(worldIn, x, y, z, 0, 0, false, false);
	}

	public BombEntity(EntityType<? extends BombEntity> p_i50159_1_, World p_i50159_2_, int power, float weight,
			boolean nuclear, boolean fire) {
		super(p_i50159_1_, p_i50159_2_);
		this.power = power;
		this.weight = weight;
		this.nuclear = nuclear;

		this.fire = fire;
	}

	public BombEntity(World worldIn, LivingEntity throwerIn, int power, float weight, boolean nuclear, boolean fire) {
		super(EntityType.SNOWBALL, throwerIn, worldIn);
		this.power = power;
		this.weight = weight;
		this.nuclear = nuclear;
		this.fire = fire;
	}

	public BombEntity(World worldIn, double x, double y, double z, int power, float weight, boolean nuclear,
			boolean fire) {
		super(EntityType.SNOWBALL, x, y, z, worldIn);
		this.power = power;
		this.weight = weight;
		this.nuclear = nuclear;
		this.fire = fire;
	}

	protected Item func_213885_i() {
		return Items.SNOWBALL;
	}

	@OnlyIn(Dist.CLIENT)
	private IParticleData func_213887_n() {
		ItemStack itemstack = this.func_213882_k();
		return (IParticleData) (itemstack.isEmpty() ? ParticleTypes.ITEM_SNOWBALL
				: new ItemParticleData(ParticleTypes.ITEM, itemstack));
	}

	/**
	 * Handler for {@link World#setEntityState}
	 */
	@OnlyIn(Dist.CLIENT)
	public void handleStatusUpdate(byte id) {
		if (id == 3) {
			IParticleData iparticledata = this.func_213887_n();

			for (int i = 0; i < 8; ++i) {
				this.world.addParticle(iparticledata, this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D);
			}
		}

	}

	public float getGravityVelocity() {
		return this.weight / 100;
	}

	public float getVelocity() {
		return 5F;
	}

	/**
	 * Called when this EntityThrowable hits a block or entity.
	 */
	protected void onImpact(RayTraceResult result) {
		// NEED NUCLEAR EFFECT
		if (!this.world.isRemote) {
			Vec3d pos = result.getHitVec();
			// @formatter:off
//			Minecraft.getInstance().player.sendMessage(new StringTextComponent(
//					result.getType() == RayTraceResult.Type.BLOCK ?
//							"block "+ this.world.getBlockState(((BlockRayTraceResult) result).getPos()).toString()
//									+ " " + ((BlockRayTraceResult) result).getPos().toString() 
//							: "entity"));

			// @formatter:on
			this.world.createExplosion(null, pos.x, pos.y, pos.z, this.power, this.fire, Explosion.Mode.DESTROY);
			if (this.nuclear) {
				
			}
//			this.world.setBlockState(new BlockPos((int) pos.x, (int) pos.y, (int) pos.z),
//					Blocks.OBSIDIAN.getDefaultState());
			this.remove();
		}

	}
}
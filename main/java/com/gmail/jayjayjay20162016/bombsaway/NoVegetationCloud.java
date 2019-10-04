package com.gmail.jayjayjay20162016.bombsaway;

import java.util.stream.Stream;

import net.minecraft.block.AbstractCoralPlantBlock;
import net.minecraft.block.AbstractGlassBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.ChorusFlowerBlock;
import net.minecraft.block.ChorusPlantBlock;
import net.minecraft.block.FlowerPotBlock;
import net.minecraft.block.HugeMushroomBlock;
import net.minecraft.block.IGrowable;
import net.minecraft.block.KelpBlock;
import net.minecraft.block.KelpTopBlock;
import net.minecraft.block.LeavesBlock;
import net.minecraft.block.LogBlock;
import net.minecraft.block.MyceliumBlock;
import net.minecraft.block.SpongeBlock;
import net.minecraft.block.StemGrownBlock;
import net.minecraft.block.VineBlock;
import net.minecraft.entity.AreaEffectCloudEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.placement.ChorusPlant;
import net.minecraftforge.common.IPlantable;

public class NoVegetationCloud extends AreaEffectCloudEntity {

	public NoVegetationCloud(EntityType<? extends AreaEffectCloudEntity> p_i50389_1_, World p_i50389_2_) {
		super(p_i50389_1_, p_i50389_2_);
		// TODO Auto-generated constructor stub
	}

	public NoVegetationCloud(World worldIn, double x, double y, double z) {
		super(worldIn, x, y, z);
		// TODO Auto-generated constructor stub
	}

	private boolean blockInTag(Block block, String tag) {
		ResourceLocation myTagId = new ResourceLocation("forge", tag);
		boolean isInGroup = BlockTags.getCollection().getOrCreate(myTagId).contains(block);
		return isInGroup;
	}

	public void tick() {
		super.tick();
		Stream<BlockPos> box = BlockPos.getAllInBox((int) (this.posX - this.getRadius()), 0,
				(int) (this.posZ - this.getRadius()), (int) (this.posX + this.getRadius()), 255,
				(int) (this.posZ + this.getRadius()));
		box.filter((BlockPos pos) -> (new BlockPos(pos.getX(), 0, pos.getZ()))
				.distanceSq((new BlockPos(this.getPosition().getX(), 0, this.getPosition().getZ()))) < Math
						.pow(this.getRadius(), 2));
		BlockPos[] boxa = (BlockPos[]) box.toArray();
		for (BlockPos pos : boxa) {
			// STILL HAVE SOME UNSOLVED CASES
			// CRASHES ON SPAWN FROM /summon
			BlockState blockstate = this.world.getBlockState(pos);
			Block block = blockstate.getBlock();
			if (block instanceof IGrowable || block instanceof IPlantable || block instanceof StemGrownBlock
					|| block instanceof FlowerPotBlock || block instanceof AbstractGlassBlock
					|| block instanceof HugeMushroomBlock || block instanceof AbstractCoralPlantBlock
					|| block instanceof LeavesBlock || block instanceof LogBlock || block instanceof VineBlock
					|| block instanceof KelpBlock || block instanceof KelpTopBlock || block instanceof SpongeBlock
					|| block instanceof MyceliumBlock || block instanceof ChorusPlantBlock
					|| block instanceof ChorusFlowerBlock) {
				world.setBlockState(pos, Blocks.AIR.getDefaultState());
			}
		}
	}
}

package com.gmail.jayjayjay20162016.bombsaway;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.SnowballEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;

public class ItemBomb extends Item {
	private int power;
	private boolean nuclear;
	private int weight;
	private boolean fire;

	public ItemBomb(int power, boolean nuclear, boolean fire, int weight) {
		super(new Item.Properties().group(BombsAway.bombs).maxStackSize(1));
		this.power = power;
		this.nuclear = nuclear;
		this.fire = fire;
		this.weight = weight;
	}

	public ItemBomb(int power, boolean nuclear, boolean fire) {
		super(new Item.Properties().group(BombsAway.bombs).maxStackSize(1));

		this.power = power;
		this.nuclear = nuclear;
		this.fire = fire;
		this.weight = nuclear ? fire ? power * 4 : power * 2 : fire ? power * 2 : power;
	}

	public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
		ItemStack itemstack = playerIn.getHeldItem(handIn);
		if (!playerIn.abilities.isCreativeMode) {
			itemstack.shrink(1);
		}

		if (!worldIn.isRemote) {
			BombEntity entity = new BombEntity(worldIn, playerIn, this.power, this.weight, this.nuclear, this.fire);
			entity.func_213884_b(itemstack);
			entity.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 1.5F, 1.0F);
			worldIn.addEntity(entity);
		}

		return new ActionResult<>(ActionResultType.SUCCESS, itemstack);
	}

}

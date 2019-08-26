package com.gmail.jayjayjay20162016.bombsaway;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class BombItemGroup extends ItemGroup {

	public BombItemGroup(String label) {
		super(label);
		// TODO Auto-generated constructor stub
	}

	public BombItemGroup(int index, String label) {
		super(index, label);
		// TODO Auto-generated constructor stub
	}

	@Override
	public ItemStack createIcon() {
		// TODO Auto-generated method stub
		return new ItemStack(ItemList.hibomb);
	}

}

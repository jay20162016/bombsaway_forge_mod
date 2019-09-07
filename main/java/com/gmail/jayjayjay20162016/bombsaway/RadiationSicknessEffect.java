package com.gmail.jayjayjay20162016.bombsaway;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AbstractAttributeMap;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.EffectType;
import net.minecraft.potion.Effects;

public class RadiationSicknessEffect extends Effect {

	public RadiationSicknessEffect() {
		super(EffectType.HARMFUL, 65280);
		this.addAttributesModifier(SharedMonsterAttributes.MAX_HEALTH, "5D6F0BA2-1186-46AC-B896-C61C5CEE99CC", -8.0D,
				AttributeModifier.Operation.ADDITION);

	}
	
	public List<ItemStack> getCurativeItems() {
		return new ArrayList<ItemStack>();
	}

	public void removeAttributesModifiersFromEntity(LivingEntity entityLivingBaseIn,
			AbstractAttributeMap attributeMapIn, int amplifier) {
		super.removeAttributesModifiersFromEntity(entityLivingBaseIn, attributeMapIn, amplifier);
		if (entityLivingBaseIn instanceof PlayerEntity) {
			PlayerEntity player = (PlayerEntity) entityLivingBaseIn;
		}
	}
}

package com.gmail.jayjayjay20162016.bombsaway;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.annotation.Nullable;

import net.minecraft.entity.Entity;
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
import net.minecraft.util.DamageSource;

public class RadiationEffect extends Effect {
	protected static final UUID MAX_HEALTH_UUID = UUID.fromString("66aa4f51-77db-485e-9380-967977c7d9f4");
	protected static final DamageSource RADIOACTIVITY = (new DamageSource("radioactivity")).setDamageBypassesArmor()
			.setDamageIsAbsolute();

	public RadiationEffect() {
		super(EffectType.HARMFUL, 65280);
//		this.addAttributesModifier(SharedMonsterAttributes.MAX_HEALTH, "5D6F0BA2-1186-46AC-B896-C61C5CEE99CC", -8.0D,
//				AttributeModifier.Operation.ADDITION);
	}

	/// IS REMOVED BY MILK 
	public List<ItemStack> getCurativeItems() {
		return new ArrayList<ItemStack>();
	}

	public void performEffect(LivingEntity entityLivingBaseIn, int amplifier) {
		entityLivingBaseIn.attackEntityFrom(RADIOACTIVITY, (amplifier + 1) / 2);
		entityLivingBaseIn.addPotionEffect(new EffectInstance(EffectList.radiationsick, (amplifier + 1) * 250000));
	}

	public void removeAttributesModifiersFromEntity(LivingEntity entityLivingBaseIn,
			AbstractAttributeMap attributeMapIn, int amplifier) {
		super.removeAttributesModifiersFromEntity(entityLivingBaseIn, attributeMapIn, amplifier);
//		if (entityLivingBaseIn instanceof PlayerEntity) {
//			PlayerEntity player = (PlayerEntity) entityLivingBaseIn;
//			player.addPotionEffect(new EffectInstance(EffectList.radiationsick, (amplifier + 1) * 250000));
//
//		}
//		entityLivingBaseIn.addPotionEffect(new EffectInstance(EffectList.radiationsick, (amplifier + 1) * 250000));
	}

	public void affectEntity(@Nullable Entity source, @Nullable Entity indirectSource, LivingEntity entityLivingBaseIn,
			int amplifier, double health) {

	}

	public boolean isReady(int duration, int amplifier) {
		int i = 40 >> amplifier;
		if (i > 0) {
			return duration % i == 0;
		} else {
			return true;
		}
	}

}

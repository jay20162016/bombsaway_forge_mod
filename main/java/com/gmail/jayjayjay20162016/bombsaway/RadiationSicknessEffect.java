package com.gmail.jayjayjay20162016.bombsaway;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AbstractAttributeMap;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;

public class RadiationSicknessEffect extends Effect {

	public RadiationSicknessEffect() {
		super(EffectType.HARMFUL, 65280);
	}

	public void removeAttributesModifiersFromEntity(LivingEntity entityLivingBaseIn,
			AbstractAttributeMap attributeMapIn, int amplifier) {
		super.removeAttributesModifiersFromEntity(entityLivingBaseIn, attributeMapIn, amplifier);
		if (entityLivingBaseIn instanceof PlayerEntity) {
			PlayerEntity player = (PlayerEntity) entityLivingBaseIn;
			player.getAttribute(SharedMonsterAttributes.MAX_HEALTH).removeModifier(new AttributeModifier(
					"5D6F0BA2-1186-46AC-B896-C61C5CEE99CC", -8.0D, AttributeModifier.Operation.ADDITION));
		}
	}
}

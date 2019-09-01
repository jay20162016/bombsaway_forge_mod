package com.gmail.jayjayjay20162016.bombsaway;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AbstractAttributeMap;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.Effect;
<<<<<<< HEAD
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.EffectType;
import net.minecraft.potion.Effects;
=======
import net.minecraft.potion.EffectType;
>>>>>>> d82681819d0b2992a8f0aeee2f249b88cd5d6626

public class RadiationSicknessEffect extends Effect {

	public RadiationSicknessEffect() {
		super(EffectType.HARMFUL, 65280);
<<<<<<< HEAD
		this.addAttributesModifier(SharedMonsterAttributes.MAX_HEALTH, "5D6F0BA2-1186-46AC-B896-C61C5CEE99CC", -8.0D,
				AttributeModifier.Operation.ADDITION);

	}

	public void performEffect(LivingEntity entityLivingBaseIn, int amplifier) {
		entityLivingBaseIn.addPotionEffect(new EffectInstance(Effects.HUNGER));
	}

	/*
	 * /// DOESN'T CRASH ON MILK AND IS REMOVED BY MILK public List<ItemStack>
	 * getCurativeItems() { return new ArrayList<ItemStack>(); }
	 */
=======
	}

>>>>>>> d82681819d0b2992a8f0aeee2f249b88cd5d6626
	public void removeAttributesModifiersFromEntity(LivingEntity entityLivingBaseIn,
			AbstractAttributeMap attributeMapIn, int amplifier) {
		super.removeAttributesModifiersFromEntity(entityLivingBaseIn, attributeMapIn, amplifier);
		if (entityLivingBaseIn instanceof PlayerEntity) {
			PlayerEntity player = (PlayerEntity) entityLivingBaseIn;
<<<<<<< HEAD
=======
			player.getAttribute(SharedMonsterAttributes.MAX_HEALTH).removeModifier(new AttributeModifier(
					"5D6F0BA2-1186-46AC-B896-C61C5CEE99CC", -8.0D, AttributeModifier.Operation.ADDITION));
>>>>>>> d82681819d0b2992a8f0aeee2f249b88cd5d6626
		}
	}
}

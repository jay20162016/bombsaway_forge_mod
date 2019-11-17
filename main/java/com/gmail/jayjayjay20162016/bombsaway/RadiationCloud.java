package com.gmail.jayjayjay20162016.bombsaway;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.AreaEffectCloudEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

public class RadiationCloud {
	public static void createRadiationCloud(int maxamp, int minamp, float radius, World world, double x, double y,
			double z) {
		assert maxamp >= minamp;
		assert radius > 0;
		Minecraft.getInstance().player.sendMessage(new StringTextComponent("Nuclear Effect Created"));
		BombsAway.logger.debug(Integer.toString(maxamp));
		BombsAway.logger.debug(Integer.toString(minamp));
		BombsAway.logger.debug(Float.toString(radius));
		BombsAway.logger.debug(Double.toString(x) + " " + Double.toString(y) + " " + Double.toString(z));
		for (int i = minamp; i <= maxamp; i++) {
			Minecraft.getInstance().player.sendMessage(new StringTextComponent("Nuclear Cloud Created"));
			BombsAway.logger.debug(Integer.toString(i));
			if (i == maxamp) {
				NoVegetationCloud aecloud = new NoVegetationCloud(world, x, y, z);
				BombsAway.logger.debug(Float.toString(radius / (maxamp - minamp + 1) * (i - minamp + 1)));
				aecloud.setRadius(radius / (maxamp - minamp + 1) * (i - minamp + 1));
				aecloud.setRadiusOnUse(0F);
				aecloud.setDuration(9999999);
				aecloud.setWaitTime(100);
				aecloud.addEffect(new EffectInstance(EffectList.radiation));
				world.addEntity(aecloud);
			} else {
				AreaEffectCloudEntity aecloud = new AreaEffectCloudEntity(world, x, y, z);
				BombsAway.logger.debug(Float.toString(radius / (maxamp - minamp + 1) * (i - minamp + 1)));
				aecloud.setRadius(radius / (maxamp - minamp + 1) * (i - minamp + 1));
				aecloud.setRadiusOnUse(0F);
				aecloud.setDuration(9999999);
				aecloud.setWaitTime(100);
				aecloud.addEffect(new EffectInstance(EffectList.radiation));
				world.addEntity(aecloud);
			}
		}
	}

}

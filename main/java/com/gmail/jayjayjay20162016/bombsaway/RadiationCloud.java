package com.gmail.jayjayjay20162016.bombsaway;

import net.minecraft.entity.AreaEffectCloudEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.world.World;

public class RadiationCloud {
	public RadiationCloud(int maxamp, int minamp, float radius, World world, double x, double y, double z) {
		assert maxamp>=minamp;
		assert radius > 0;
		for (int i = minamp; i <= maxamp; i++) {
			if (i == minamp) {
				NoVegetationCloud aecloud = new NoVegetationCloud(world, x, y, z);
				aecloud.setRadius(radius/(maxamp - minamp + 1)*(i - minamp + 1));
				aecloud.setRadiusOnUse(0F);
				aecloud.setDuration(Integer.MAX_VALUE);
				aecloud.setWaitTime(100);
				aecloud.addEffect(new EffectInstance(EffectList.radiation));
				
			}
			else {
				AreaEffectCloudEntity aecloud = new AreaEffectCloudEntity(world, x, y, z);
				aecloud.setRadius(radius/(maxamp - minamp + 1)*(i - minamp + 1));
				aecloud.setRadiusOnUse(0F);
				aecloud.setDuration(Integer.MAX_VALUE);
				aecloud.setWaitTime(100);
				aecloud.addEffect(new EffectInstance(EffectList.radiation));
			}
		}
	}

}

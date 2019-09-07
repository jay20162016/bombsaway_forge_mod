package com.gmail.jayjayjay20162016.bombsaway;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraft.block.DispenserBlock;
import net.minecraft.dispenser.IPosition;
import net.minecraft.dispenser.ProjectileDispenseBehavior;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Effect;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.living.LivingHealEvent;
import net.minecraftforge.event.entity.living.PotionEvent.PotionRemoveEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

/*
 * 
 * TODO: ADD NUCLEAR EFFECT (WORKING ON THIS ONE)
 * remove milk as curative item
 * TODO: TWEAK VALUES
 * TODO: ADD RECIPES (I THINK THIS IS DONE HOWEVER IT IS NOT TESTED)
 */

@Mod(BombsAway.modid)
public class BombsAway {
	public static final String modid = "bombsaway";
	public static BombsAway instance;
	private static final Logger logger = LogManager.getLogger(modid);
	public static ItemGroup bombs = new BombItemGroup("bomb");

	public BombsAway() {
		this.instance = this;

		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::clientRegistries);

		MinecraftForge.EVENT_BUS.register(this);
	}

	private void setup(final FMLCommonSetupEvent event) {
		registerBomb(ItemList.hbomb, 70, true, false);
		registerBomb(ItemList.pbomb, 60, true, false);
		registerBomb(ItemList.ubomb, 50, true, false);
		registerBomb(ItemList.hibomb, 70, true, true);
		registerBomb(ItemList.pibomb, 60, true, true);
		registerBomb(ItemList.uibomb, 50, true, true);
		registerBomb(ItemList.lbomb, 40, false, false);
		registerBomb(ItemList.mbomb, 30, false, false);
		registerBomb(ItemList.sbomb, 20, false, false);
		registerBomb(ItemList.libomb, 40, false, true);
		registerBomb(ItemList.mibomb, 30, false, true);
		registerBomb(ItemList.sibomb, 20, false, true);
		registerBomb(ItemList.grenade, 5, false, false);

	}

	private static void registerBomb(Item item, int power, boolean nuclear, boolean fire) {
		int weight = nuclear ? fire ? power * 4 : power * 2 : fire ? power * 2 : power;
		DispenserBlock.registerDispenseBehavior(item, new ProjectileDispenseBehavior() {
			/**
			 * Return the projectile entity spawned by this dispense behavior.
			 */
			protected IProjectile getProjectileEntity(World worldIn, IPosition position, ItemStack stackIn) {

				BombEntity bombentity = new BombEntity(worldIn, position.getX(), position.getY(), position.getZ(),
						power, weight, nuclear, fire);
				return bombentity;
			};
		});
	}

	private void clientRegistries(final FMLClientSetupEvent event) {

	};
	
	// SHOULD BE REMOVED WITH entityLiving.clearActivePotions(); IN MILK
	@Mod.EventBusSubscriber
	public static class Events {
		@SubscribeEvent
		public static void preventCure(final PotionRemoveEvent event) {
			if ( event.getPotion() instanceof RadiationEffect || event.getPotion() instanceof RadiationSicknessEffect ) {
				event.setCanceled(true);
			}
		}
		
		@SubscribeEvent
		public static void preventNaturalHealing(final LivingHealEvent event) {
			LivingEntity entity = event.getEntityLiving();
			if (entity.getActivePotionEffect(EffectList.radiationsick) != null) {
				event.setCanceled(true);
			}
		}
	}
	
	@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
	public static class RegisterEvents {
		@SubscribeEvent
		public static void registerItems(final RegistryEvent<Item> event) {
			((RegistryEvent.Register<Item>) event).getRegistry().registerAll(
					ItemList.hibomb = new ItemBomb(70, true, true).setRegistryName(location("hibomb")),
					ItemList.pibomb = new ItemBomb(60, true, true).setRegistryName(location("pibomb")),
					ItemList.uibomb = new ItemBomb(50, true, true).setRegistryName(location("uibomb")),
					ItemList.hbomb = new ItemBomb(70, true, false).setRegistryName(location("hbomb")),
					ItemList.pbomb = new ItemBomb(60, true, false).setRegistryName(location("pbomb")),
					ItemList.ubomb = new ItemBomb(50, true, false).setRegistryName(location("ubomb")),
					ItemList.libomb = new ItemBomb(40, false, true).setRegistryName(location("libomb")),
					ItemList.mibomb = new ItemBomb(30, false, true).setRegistryName(location("mibomb")),
					ItemList.sibomb = new ItemBomb(20, false, true).setRegistryName(location("sibomb")),
					ItemList.lbomb = new ItemBomb(40, false, false).setRegistryName(location("lbomb")),
					ItemList.mbomb = new ItemBomb(30, false, false).setRegistryName(location("mbomb")),
					ItemList.sbomb = new ItemBomb(20, false, false).setRegistryName(location("sbomb")),
					ItemList.grenade = new ItemBomb(5, false, false).setRegistryName(location("grenade")));
		}

		@SubscribeEvent
		public static void registerEffects(final RegistryEvent<Effect> event) {
			((RegistryEvent.Register<Effect>) event).getRegistry().registerAll(
					EffectList.radiation = new RadiationEffect().setRegistryName(location("radiation")),
					EffectList.radiationsick = new RadiationSicknessEffect()
							.setRegistryName(location("radiation_sick")));
		}

		public static ResourceLocation location(String name) {
			return new ResourceLocation(modid, name);
		}
	}
}

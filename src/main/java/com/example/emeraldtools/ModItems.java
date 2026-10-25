package com.example.emeraldtools;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ToolMaterial;

public class ModItems {

	// Emerald tool material: same tier as netherite, +20 durability (2031 -> 2051)
	public static final ToolMaterial EMERALD_MATERIAL = new ToolMaterial(
			BlockTags.INCORRECT_FOR_NETHERITE_TOOL,
			2051,
			9.0F,
			4.0F,
			15,
			TagKey.create(Registries.ITEM,
					Identifier.fromNamespaceAndPath(EmeraldToolsMod.MOD_ID, "emerald_tool_materials"))
	);

	public static final Item EMERALD_SWORD = register("emerald_sword",
			props -> new Item(props.sword(EMERALD_MATERIAL, 3.0F, -2.4F)));

	public static final Item EMERALD_PICKAXE = register("emerald_pickaxe",
			props -> new Item(props.pickaxe(EMERALD_MATERIAL, 1.0F, -2.8F)));

	public static final Item EMERALD_AXE = register("emerald_axe",
			props -> new Item(props.axe(EMERALD_MATERIAL, 5.0F, -3.0F)));

	public static final Item EMERALD_SHOVEL = register("emerald_shovel",
			props -> new Item(props.shovel(EMERALD_MATERIAL, 1.5F, -3.0F)));

	public static final Item EMERALD_HOE = register("emerald_hoe",
			props -> new Item(props.hoe(EMERALD_MATERIAL, 0.0F, -4.0F)));

	private static Item register(String name, java.util.function.Function<Item.Properties, Item> factory) {
		ResourceKey<Item> key = ResourceKey.create(Registries.ITEM, EmeraldToolsMod.id(name));
		Item.Properties props = new Item.Properties().setId(key);
		Item item = factory.apply(props);
		return Registry.register(BuiltInRegistries.ITEM, key, item);
	}

	public static void register() {
		addToCreativeTabs();
	}

	@SuppressWarnings("unchecked")
	private static void addToCreativeTabs() {
		try {
			Class<?> eventsClass = Class.forName("net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents");

			Object toolsEvent = eventsClass
					.getMethod("modifyOutputEvent", ResourceKey.class)
					.invoke(null, CreativeModeTabs.TOOLS_AND_UTILITIES);
			toolsEvent.getClass().getMethod("register", Object.class).invoke(toolsEvent,
					(java.util.function.Consumer<CreativeModeTab.Output>) output -> {
						output.accept(EMERALD_SWORD);
						output.accept(EMERALD_PICKAXE);
						output.accept(EMERALD_AXE);
						output.accept(EMERALD_SHOVEL);
						output.accept(EMERALD_HOE);
					});

			Object combatEvent = eventsClass
					.getMethod("modifyOutputEvent", ResourceKey.class)
					.invoke(null, CreativeModeTabs.COMBAT);
			combatEvent.getClass().getMethod("register", Object.class).invoke(combatEvent,
					(java.util.function.Consumer<CreativeModeTab.Output>) output -> {
						output.accept(EMERALD_SWORD);
						output.accept(EMERALD_AXE);
					});
		} catch (ClassNotFoundException e) {
			EmeraldToolsMod.LOGGER.info("Fabric API not found; items are obtainable via /give command.");
		} catch (Exception e) {
			EmeraldToolsMod.LOGGER.warn("Could not add items to creative tabs: {}", e.toString());
		}
	}
}

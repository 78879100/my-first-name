package com.example.emeraldtools;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ToolMaterial;
import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;

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
			new Item(new Item.Properties().sword(EMERALD_MATERIAL, 3.0F, -2.4F)));

	public static final Item EMERALD_PICKAXE = register("emerald_pickaxe",
			new Item(new Item.Properties().pickaxe(EMERALD_MATERIAL, 1.0F, -2.8F)));

	public static final Item EMERALD_AXE = register("emerald_axe",
			new Item(new Item.Properties().axe(EMERALD_MATERIAL, 5.0F, -3.0F)));

	public static final Item EMERALD_SHOVEL = register("emerald_shovel",
			new Item(new Item.Properties().shovel(EMERALD_MATERIAL, 1.5F, -3.0F)));

	public static final Item EMERALD_HOE = register("emerald_hoe",
			new Item(new Item.Properties().hoe(EMERALD_MATERIAL, 0.0F, -4.0F)));

	private static Item register(String name, Item item) {
		return Registry.register(BuiltInRegistries.ITEM, EmeraldToolsMod.id(name), item);
	}

	public static void register() {
		CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.TOOLS_AND_UTILITIES).register(
				output -> {
					output.accept(EMERALD_SWORD);
					output.accept(EMERALD_PICKAXE);
					output.accept(EMERALD_AXE);
					output.accept(EMERALD_SHOVEL);
					output.accept(EMERALD_HOE);
				});

		CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.COMBAT).register(
				output -> {
					output.accept(EMERALD_SWORD);
					output.accept(EMERALD_AXE);
				});
	}
}

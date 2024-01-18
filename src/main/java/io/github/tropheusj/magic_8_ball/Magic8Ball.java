package io.github.tropheusj.magic_8_ball;

import io.github.tropheusj.magic_8_ball.item.IcosahedronItem;
import io.github.tropheusj.magic_8_ball.item.IncompleteIcosahedronItem;
import io.github.tropheusj.magic_8_ball.item.Magic8BallItem;
import io.github.tropheusj.magic_8_ball.recipe.SpecialRecipeSerializers;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;

import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;

import net.minecraft.world.item.Items;
import net.minecraft.world.item.Rarity;

import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.ModInitializer;
import org.quiltmc.qsl.item.setting.api.QuiltItemSettings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Magic8Ball implements ModInitializer {
	public static final String ID = "magic_8_ball";
	public static final Logger LOGGER = LoggerFactory.getLogger(ID);

	public static final Item MAGIC_8_BALL = new Magic8BallItem(new QuiltItemSettings().rarity(Rarity.RARE).stacksTo(1));
	public static final Item HEMISPHERE = new Item(new QuiltItemSettings());
	public static final Item ICOSAHEDRON = new IcosahedronItem(new QuiltItemSettings().stacksTo(1));
	public static final Item INCOMPLETE_ICOSAHEDRON = new IncompleteIcosahedronItem(new QuiltItemSettings().stacksTo(1));
	public static final Item ICOSAHEDRON_FACE = new Item(new QuiltItemSettings());

	@Override
	public void onInitialize(ModContainer mod) {
		Registry.register(BuiltInRegistries.ITEM, id("magic_8_ball"), MAGIC_8_BALL);
		Registry.register(BuiltInRegistries.ITEM, id("hemisphere"), HEMISPHERE);
		Registry.register(BuiltInRegistries.ITEM, id("icosahedron"), ICOSAHEDRON);
		Registry.register(BuiltInRegistries.ITEM, id("incomplete_icosahedron"), INCOMPLETE_ICOSAHEDRON);
		Registry.register(BuiltInRegistries.ITEM, id("icosahedron_face"), ICOSAHEDRON_FACE);

		SpecialRecipeSerializers.register();

		ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.TOOLS_AND_UTILITIES).register(
				entries -> entries.addAfter(Items.ENDER_EYE, MAGIC_8_BALL)
		);

		LootTableEvents.MODIFY.register(new Magic8BallLootModifier());
	}

	public static ResourceLocation id(String path) {
		return new ResourceLocation(ID, path);
	}
}

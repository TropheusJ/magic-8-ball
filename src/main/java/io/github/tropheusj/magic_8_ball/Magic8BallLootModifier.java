package io.github.tropheusj.magic_8_ball;

import io.github.tropheusj.magic_8_ball.item.ResponseHolder;
import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.fabricmc.fabric.api.loot.v2.LootTableSource;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootDataManager;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.SetNbtFunction;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;

import java.util.Set;

public class Magic8BallLootModifier implements LootTableEvents.Modify {
	public static final Set<ResourceLocation> LOOT_TABLES = Set.of(
			BuiltInLootTables.END_CITY_TREASURE,
			BuiltInLootTables.SIMPLE_DUNGEON,
			BuiltInLootTables.ABANDONED_MINESHAFT,
			BuiltInLootTables.STRONGHOLD_LIBRARY,
			BuiltInLootTables.STRONGHOLD_CORRIDOR,
			BuiltInLootTables.STRONGHOLD_CROSSING,
			BuiltInLootTables.JUNGLE_TEMPLE,
			BuiltInLootTables.DESERT_PYRAMID,
			BuiltInLootTables.ANCIENT_CITY
	);

	@Override
	public void modifyLootTable(ResourceManager resourceManager, LootDataManager lootManager, ResourceLocation id, LootTable.Builder tableBuilder, LootTableSource source) {
		if (LOOT_TABLES.contains(id)) {
//			CompoundTag nbt = ResponseHolder.create(Magic8Ball.MAGIC_8_BALL, BuiltInResponses.ALL).getTag();
//			tableBuilder.withPool(LootPool.lootPool()
//					.setRolls(ConstantValue.exactly(1))
//					.setBonusRolls(ConstantValue.exactly(0))
//					.add(LootItem.lootTableItem(Magic8Ball.MAGIC_8_BALL)
//							.apply(SetNbtFunction.setTag(nbt))));
		}
	}
}

package io.github.tropheusj.magic_8_ball.loot;

import io.github.tropheusj.magic_8_ball.Magic8Ball;
import it.unimi.dsi.fastutil.objects.Object2IntArrayMap;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.fabricmc.fabric.api.loot.v2.LootTableSource;
import net.minecraft.Util;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootDataManager;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.EmptyLootItem;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;

import java.util.Set;

public class Magic8BallLootModifier implements LootTableEvents.Modify {
	public static final Set<ResourceLocation> REGULAR_LOOT_TABLES = Set.of(
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
	public static final Object2IntMap<ResourceLocation> SINGLE_ITEM_LOOT_TABLE_WEIGHTS = Util.make(() -> {
		Object2IntMap<ResourceLocation> map = new Object2IntArrayMap<>();
		map.put(BuiltInLootTables.FISHING_TREASURE, 1);
		map.put(BuiltInLootTables.PIGLIN_BARTERING, 8);
		return map;
	});

	@Override
	public void modifyLootTable(ResourceManager resourceManager, LootDataManager lootManager, ResourceLocation id, LootTable.Builder tableBuilder, LootTableSource source) {
		if (REGULAR_LOOT_TABLES.contains(id)) {
			tableBuilder.withPool(LootPool.lootPool()
					.setRolls(ConstantValue.exactly(1))
					.setBonusRolls(ConstantValue.exactly(0))
					.add(LootItem.lootTableItem(Magic8Ball.MAGIC_8_BALL)
							.apply(SetRandomPresetFunction::new)
							.setWeight(1)) // appear in 1/3rd
					.add(EmptyLootItem.emptyItem().setWeight(3)));
		} else if (SINGLE_ITEM_LOOT_TABLE_WEIGHTS.containsKey(id)) {
			int weight = SINGLE_ITEM_LOOT_TABLE_WEIGHTS.getInt(id);
			tableBuilder.modifyPools(pool -> pool.add(
					LootItem.lootTableItem(Magic8Ball.MAGIC_8_BALL)
							.apply(SetRandomPresetFunction::new)
							.setWeight(weight))
			);
		}
	}
}

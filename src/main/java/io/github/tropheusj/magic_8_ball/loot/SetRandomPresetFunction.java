package io.github.tropheusj.magic_8_ball.loot;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;

import io.github.tropheusj.magic_8_ball.BuiltInResponses;
import io.github.tropheusj.magic_8_ball.Magic8Ball;
import io.github.tropheusj.magic_8_ball.item.ResponseHolder;
import it.unimi.dsi.fastutil.objects.Object2IntArrayMap;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.RandomSource;
import net.minecraft.util.random.SimpleWeightedRandomList;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.functions.LootItemFunction;
import net.minecraft.world.level.storage.loot.functions.LootItemFunctionType;

import org.jetbrains.annotations.NotNull;

public class SetRandomPresetFunction implements LootItemFunction {
	public static final SimpleWeightedRandomList<BuiltInResponses.Preset> PRESETS = SimpleWeightedRandomList.<BuiltInResponses.Preset>builder()
			.add(BuiltInResponses.STANDARD, 6)
			.add(BuiltInResponses.RUDE, 4)
			.add(BuiltInResponses.OMINOUS, 4)
			.add(BuiltInResponses.CONFIDENT, 4)
			.add(BuiltInResponses.PESSIMISTIC, 4)
			.add(BuiltInResponses.OPTIMISTIC, 4)
			.add(BuiltInResponses.PARANOID, 4)
			.add(BuiltInResponses.D20, 3)
			.add(BuiltInResponses.SMALL_MAN_TRAPPED_IN_BALL, 2)
			.add(BuiltInResponses.SMALL_SALESMAN_TRAPPED_IN_BALL, 1)
			.build();

	@Override
	public ItemStack apply(ItemStack stack, LootContext lootContext) {
		return PRESETS.getRandomValue(lootContext.getRandom()).map(preset -> {
			CompoundTag nbt = ResponseHolder.create(Magic8Ball.MAGIC_8_BALL, preset.responses()).getTag();
			stack.getOrCreateTag().merge(nbt);
			return stack;
		}).orElse(stack);
	}

	@Override
	@NotNull
	public LootItemFunctionType getType() {
		return Magic8Ball.SET_RANDOM_PRESET;
	}

	public static class Serializer implements net.minecraft.world.level.storage.loot.Serializer<SetRandomPresetFunction> {
		@Override
		public void serialize(JsonObject json, SetRandomPresetFunction object, JsonSerializationContext context) {
		}

		@Override
		@NotNull
		public SetRandomPresetFunction deserialize(JsonObject json, JsonDeserializationContext context) {
			return new SetRandomPresetFunction();
		}
	}

	public static void testChances() {
		RandomSource random = RandomSource.create();
		Object2IntMap<BuiltInResponses.Preset> counts = new Object2IntArrayMap<>();
		for (int i = 0; i < 100; i++) {
			PRESETS.getRandomValue(random).ifPresent(preset -> {
				if (counts.containsKey(preset)) {
					counts.put(preset, counts.getInt(preset) + 1);
				} else {
					counts.put(preset, 1);
				}
			});
		}
		System.out.println("Summary:");
		counts.forEach((preset, count) -> System.out.println(preset.name() + ": " + count));
	}
}

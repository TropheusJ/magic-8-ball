package io.github.tropheusj.magic_8_ball.item;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.StringTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

public interface ResponseHolder {
	String RESPONSES_KEY = "responses";

	static List<MutableComponent> getResponses(ItemStack stack) {
		CompoundTag tag = stack.getTag();
		if (tag == null || !tag.contains(RESPONSES_KEY, Tag.TAG_LIST))
			return List.of();

		ListTag responses = tag.getList(RESPONSES_KEY, Tag.TAG_STRING);
		if (responses.isEmpty())
			return List.of();

		List<MutableComponent> deserialized = new ArrayList<>();
		for (int i = 0; i < responses.size(); i++) {
			String serialized = responses.getString(i);
			MutableComponent component = Component.Serializer.fromJson(serialized);
			if (component != null) {
				deserialized.add(component);
			}
		}

		return deserialized;
	}

	static ItemStack create(Item item, List<? extends Component> responses) {
		ListTag responsesTag = new ListTag();
		for (Component response : responses) {
			String serialized = Component.Serializer.toJson(response);
			responsesTag.add(StringTag.valueOf(serialized));
		}

		ItemStack stack = new ItemStack(item);
		stack.getOrCreateTag().put(RESPONSES_KEY, responsesTag);
		return stack;
	}
}

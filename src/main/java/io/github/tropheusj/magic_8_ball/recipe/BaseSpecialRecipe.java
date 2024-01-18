package io.github.tropheusj.magic_8_ball.recipe;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.CustomRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseSpecialRecipe extends CustomRecipe {
	public BaseSpecialRecipe(ResourceLocation id, CraftingBookCategory category) {
		super(id, category);
	}

	@Override
	@NotNull
	public abstract RecipeSerializer<?> getSerializer();

	public static ItemStack find(Item item, CraftingContainer container) {
		for (int i = 0; i < container.getContainerSize(); i++) {
			ItemStack stack = container.getItem(i);
			if (stack.is(item))
				return stack;
		}
		return ItemStack.EMPTY;
	}

	public static List<ItemStack> findAll(Item item, CraftingContainer container) {
		List<ItemStack> stacks = new ArrayList<>();
		for (int i = 0; i < container.getContainerSize(); i++) {
			ItemStack stack = container.getItem(i);
			if (stack.is(item))
				stacks.add(stack);
		}
		return stacks;
	}
}

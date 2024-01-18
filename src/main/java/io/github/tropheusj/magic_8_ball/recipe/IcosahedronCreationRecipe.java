package io.github.tropheusj.magic_8_ball.recipe;

import io.github.tropheusj.magic_8_ball.Magic8Ball;
import io.github.tropheusj.magic_8_ball.item.ResponseHolder;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.RecipeSerializer;

import net.minecraft.world.level.Level;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class IcosahedronCreationRecipe extends BaseSpecialRecipe {
	public IcosahedronCreationRecipe(ResourceLocation id, CraftingBookCategory category) {
		super(id, category);
	}

	@Override
	public boolean matches(CraftingContainer inventory, Level world) {
		int faces = 0;
		for (int i = 0; i < inventory.getContainerSize(); i++) {
			ItemStack stack = inventory.getItem(i);
			if (stack.isEmpty())
				continue;
			if (stack.is(Magic8Ball.ICOSAHEDRON_FACE)) {
				if (!stack.hasCustomHoverName()) {
					return false;
				}
				faces++;
			} else {
				return false;
			}
		}
		return faces >= 2;
	}

	@Override
	@NotNull
	public ItemStack assemble(CraftingContainer inventory, RegistryAccess registries) {
		List<ItemStack> faces = findAll(Magic8Ball.ICOSAHEDRON_FACE, inventory);
		List<MutableComponent> responses = faces.stream().map(ItemStack::getHoverName).map(Component::plainCopy).toList();
		return ResponseHolder.create(Magic8Ball.INCOMPLETE_ICOSAHEDRON, responses);
	}

	@Override
	public boolean canCraftInDimensions(int width, int height) {
		return width * height >= 2;
	}

	@Override
	@NotNull
	public RecipeSerializer<?> getSerializer() {
		return SpecialRecipeSerializers.ICOSAHEDRON_CREATION;
	}
}

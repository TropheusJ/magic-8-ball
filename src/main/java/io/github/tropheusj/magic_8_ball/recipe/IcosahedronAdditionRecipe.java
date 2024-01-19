package io.github.tropheusj.magic_8_ball.recipe;

import io.github.tropheusj.magic_8_ball.Magic8Ball;
import io.github.tropheusj.magic_8_ball.item.IcosahedronItem;
import io.github.tropheusj.magic_8_ball.item.ResponseHolder;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.Level;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class IcosahedronAdditionRecipe extends BaseSpecialRecipe {
	public IcosahedronAdditionRecipe(ResourceLocation id, CraftingBookCategory category) {
		super(id, category);
	}

	@Override
	public boolean matches(CraftingContainer inventory, Level world) {
		ItemStack icosahedron = null;
		int faces = 0;
		for (int i = 0; i < inventory.getContainerSize(); i++) {
			ItemStack stack = inventory.getItem(i);
			if (stack.isEmpty())
				continue;
			if (stack.is(Magic8Ball.INCOMPLETE_ICOSAHEDRON)) {
				if (icosahedron != null) {
					return false;
				}
				icosahedron = stack;
			} else if (stack.is(Magic8Ball.ICOSAHEDRON_FACE)) {
				if (!stack.hasCustomHoverName()) {
					return false;
				}
				faces++;
			} else {
				return false;
			}
		}
		if (icosahedron == null || faces == 0)
			return false;
		int needed = IcosahedronItem.FACES - ResponseHolder.getResponses(icosahedron).size();
		return needed >= faces;
	}

	@Override
	@NotNull
	public ItemStack assemble(CraftingContainer inventory, RegistryAccess registryManager) {
		ItemStack icosahedron = find(Magic8Ball.INCOMPLETE_ICOSAHEDRON, inventory);
		List<MutableComponent> responses = ResponseHolder.getResponses(icosahedron);
		List<ItemStack> faces = findAll(Magic8Ball.ICOSAHEDRON_FACE, inventory);
		for (ItemStack face : faces) {
			responses.add(face.getHoverName().plainCopy());
		}
		Item item = responses.size() == IcosahedronItem.FACES ? Magic8Ball.ICOSAHEDRON : Magic8Ball.INCOMPLETE_ICOSAHEDRON;
		return ResponseHolder.create(item, responses);
	}

	@Override
	public boolean canCraftInDimensions(int width, int height) {
		return width * height >= 2;
	}

	@Override
	@NotNull
	public RecipeSerializer<?> getSerializer() {
		return SpecialRecipeSerializers.ICOSAHEDRON_ADDITION;
	}
}

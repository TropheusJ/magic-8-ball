package io.github.tropheusj.magic_8_ball.recipe;

import io.github.tropheusj.magic_8_ball.Magic8Ball;
import io.github.tropheusj.magic_8_ball.item.IcosahedronItem;
import io.github.tropheusj.magic_8_ball.item.ResponseHolder;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.Level;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class Magic8BallRecipe extends BaseSpecialRecipe {
	public static final int COMPONENTS = 4; // icosahedron, 2 hemispheres, water

	public Magic8BallRecipe(ResourceLocation id, CraftingBookCategory category) {
		super(id, category);
	}

	@Override
	public boolean matches(CraftingContainer container, Level level) {
		ItemStack icosahedron = null;
		int hemispheres = 0;
		int buckets = 0;
		for (int i = 0; i < container.getContainerSize(); i++) {
			ItemStack stack = container.getItem(i);
			if (stack.isEmpty())
				continue;
			if (stack.is(Magic8Ball.ICOSAHEDRON)) {
				if (icosahedron != null) {
					return false;
				}
				icosahedron = stack;
			} else if (stack.is(Magic8Ball.HEMISPHERE)) {
				hemispheres++;
			} else if (stack.is(Items.WATER_BUCKET)) {
				buckets++;
			} else {
				return false;
			}
		}
		if (icosahedron == null || hemispheres != 2 || buckets != 1)
			return false;
		return ResponseHolder.getResponses(icosahedron).size() == IcosahedronItem.FACES;
	}

	@Override
	@NotNull
	public ItemStack assemble(CraftingContainer container, RegistryAccess registries) {
		ItemStack icosahedron = find(Magic8Ball.ICOSAHEDRON, container);
		List<MutableComponent> responses = ResponseHolder.getResponses(icosahedron);
		return ResponseHolder.create(Magic8Ball.MAGIC_8_BALL, responses);
	}

	@Override
	public boolean canCraftInDimensions(int width, int height) {
		return width * height >= COMPONENTS;
	}

	@Override
	@NotNull
	public RecipeSerializer<?> getSerializer() {
		return SpecialRecipeSerializers.MAGIC_8_BALL;
	}
}

package io.github.tropheusj.magic_8_ball.recipe;

import io.github.tropheusj.magic_8_ball.Magic8Ball;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.SimpleCraftingRecipeSerializer;

public class SpecialRecipeSerializers {
	public static final RecipeSerializer<Magic8BallRecipe> MAGIC_8_BALL = new SimpleCraftingRecipeSerializer<>(Magic8BallRecipe::new);
	public static final RecipeSerializer<IcosahedronCreationRecipe> ICOSAHEDRON_CREATION = new SimpleCraftingRecipeSerializer<>(IcosahedronCreationRecipe::new);
	public static final RecipeSerializer<IcosahedronAdditionRecipe> ICOSAHEDRON_ADDITION = new SimpleCraftingRecipeSerializer<>(IcosahedronAdditionRecipe::new);

	public static void register() {
		register(MAGIC_8_BALL, "magic_8_ball");
		register(ICOSAHEDRON_CREATION, "icosahedron_creation");
		register(ICOSAHEDRON_ADDITION, "icosahedron_addition");
	}

	private static void register(RecipeSerializer<?> serializer, String name) {
		Registry.register(BuiltInRegistries.RECIPE_SERIALIZER, Magic8Ball.id(name), serializer);
	}
}

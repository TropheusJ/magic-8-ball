package io.github.tropheusj.magic_8_ball.item;

import io.github.tropheusj.magic_8_ball.Magic8Ball;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluids;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class Magic8BallItem extends Item {

	public static final Component NO_RESPONSE = Component.translatable("magic_8_ball.no_response");
	public static final String RESPONSE_TRANSLATION_KEY = "magic_8_ball.response";
	public static final int COOLDOWN_TICKS = 30;

	public Magic8BallItem(Properties settings) {
		super(settings);
	}

	@Override
	@NotNull
	public InteractionResultHolder<ItemStack> use(Level world, Player user, InteractionHand hand) {
		ItemStack stack = user.getItemInHand(hand);
		if (user.isShiftKeyDown()) {
			return this.disassemble(user, stack, world);
		} else {
			return this.answerPlayer(user, stack, hand, world.random);
		}
	}

	protected InteractionResultHolder<ItemStack> disassemble(Player user, ItemStack stack, Level level) {
		List<MutableComponent> responses = ResponseHolder.getResponses(stack);
		List.of(
				ResponseHolder.create(Magic8Ball.ICOSAHEDRON, responses),
				new ItemStack(Magic8Ball.HEMISPHERE, 2)
		).forEach(part -> user.getInventory().placeItemBackInInventory(part));

		BlockPos puddlePos = user.blockPosition();
		List<BlockPos> puddlePositions = List.of(
				puddlePos,
				puddlePos.north(),
				puddlePos.south(),
				puddlePos.east(),
				puddlePos.west()
		);
		BlockState puddle = Fluids.FLOWING_WATER.defaultFluidState()
				.setValue(FlowingFluid.LEVEL, 2)
				.createLegacyBlock();
		for (BlockPos pos : puddlePositions) {
			if (user.mayBuild() && user.mayInteract(level, pos) && level.getBlockState(pos).canBeReplaced(Fluids.FLOWING_WATER)) {
				level.setBlockAndUpdate(pos, puddle);
			}
		}

		stack.shrink(1);
		return InteractionResultHolder.success(stack);
	}

	protected InteractionResultHolder<ItemStack> answerPlayer(Player user, ItemStack stack, InteractionHand hand, RandomSource random) {
		Component response = getResponse(stack, random);
		user.getCooldowns().addCooldown(this, COOLDOWN_TICKS);
		user.displayClientMessage(response, true);
		user.swing(hand);
		return InteractionResultHolder.success(stack);
	}

	protected Component getResponse(ItemStack stack, RandomSource random) {
		List<MutableComponent> responses = ResponseHolder.getResponses(stack);
		if (responses.isEmpty())
			return NO_RESPONSE;

		int index = random.nextInt(responses.size());
		MutableComponent formatted = responses.get(index).withStyle(ChatFormatting.ITALIC);
		return Component.translatable(RESPONSE_TRANSLATION_KEY, formatted);
	}
}

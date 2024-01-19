package io.github.tropheusj.magic_8_ball.item;

import io.github.tropheusj.magic_8_ball.Magic8Ball;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class IcosahedronItem extends Item {
	public static final int FACES = 20;

	public IcosahedronItem(Properties settings) {
		super(settings);
	}

	@Override
	@NotNull
	public InteractionResultHolder<ItemStack> use(Level world, Player user, InteractionHand hand) {
		// disassemble into 20 faces
		ItemStack stack = user.getItemInHand(hand);
		List<MutableComponent> responses = ResponseHolder.getResponses(stack);

		for (MutableComponent response : responses) {
			ItemStack face = new ItemStack(Magic8Ball.ICOSAHEDRON_FACE);
			face.setHoverName(response);
			user.getInventory().placeItemBackInInventory(face);
		}

		user.playSound(SoundEvents.ITEM_FRAME_REMOVE_ITEM);

		stack.shrink(1);
		return InteractionResultHolder.success(stack);
	}
}

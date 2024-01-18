package io.github.tropheusj.magic_8_ball.item;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import org.jetbrains.annotations.Nullable;

import java.util.List;

public class IncompleteIcosahedronItem extends IcosahedronItem { // inherit disassembly
	public static final String FACE_COUNT_KEY = "icosahedron.incomplete.faces";

	public IncompleteIcosahedronItem(Properties settings) {
		super(settings);
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level world, List<Component> tooltip, TooltipFlag context) {
		int faces = ResponseHolder.getResponses(stack).size();
		Component completion = Component.translatable(FACE_COUNT_KEY, faces, FACES);
		tooltip.add(completion);
		super.appendHoverText(stack, world, tooltip, context);
	}
}

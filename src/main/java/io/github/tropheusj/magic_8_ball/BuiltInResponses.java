package io.github.tropheusj.magic_8_ball;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;

import java.util.ArrayList;
import java.util.List;

public class BuiltInResponses {
	public static final String KEY_ROOT = "magic_8_ball.response.";

	public static final List<Component> ALL = new ArrayList<>();

	public static final Component IT_IS_CERTAIN = create("it_is_certain");
	public static final Component IT_IS_DECIDEDLY_SO = create("it_is_decidedly_so");
	public static final Component WITHOUT_A_DOUBT = create("without_a_doubt");
	public static final Component YES_DEFINITELY = create("yes_definitely");
	public static final Component YOU_MAY_RELY_ON_IT = create("you_may_rely_on_it");

	public static final Component AS_I_SEE_IT_YES = create("as_i_see_it_yes");
	public static final Component MOST_LIKELY = create("most_likely");
	public static final Component OUTLOOK_GOOD = create("outlook_good");
	public static final Component YES = create("yes");
	public static final Component SIGNS_POINT_TO_YES = create("signs_point_to_yes");

	public static final Component REPLY_HAZY_TRY_AGAIN = create("reply_hazy_try_again");
	public static final Component ASK_AGAIN_LATER = create("ask_again_later");
	public static final Component BETTER_NOT_TELL_YOU_NOW = create("better_not_tell_you_now");
	public static final Component CANNOT_PREDICT_NOW = create("cannot_predict_now");
	public static final Component CONCENTRATE_AND_ASK_AGAIN = create("concentrate_and_ask_again");

	public static final Component DONT_COUNT_ON_IT = create("dont_count_on_it");
	public static final Component MY_REPLY_IS_NO = create("my_reply_is_no");
	public static final Component MY_SOURCES_SAY_NO = create("my_sources_say_no");
	public static final Component OUTLOOK_NOT_SO_GOOD = create("outlook_not_so_good");
	public static final Component VERY_DOUBTFUL = create("very_doubtful");

	private static Component create(String name) {
		MutableComponent component = Component.translatable(KEY_ROOT + name);
		ALL.add(component);
		return component;
	}
}

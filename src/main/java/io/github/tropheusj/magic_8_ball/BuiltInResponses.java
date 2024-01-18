package io.github.tropheusj.magic_8_ball;

import io.github.tropheusj.magic_8_ball.item.IcosahedronItem;
import net.minecraft.network.chat.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class BuiltInResponses {
	public static final Preset STANDARD = new Preset.Builder("standard")
			.add("It is certain.", "It is decidedly so.", "Without a doubt.", "Yes, definitely.", "You may rely on it.")
			.add("As I see it, yes.", "Most likely.", "Outlook good.", "Yes.", "Signs point to yes.")
			.add("Reply hazy, try again.", "Ask again later.", "Better not tell you now.", "Cannot predict now.", "Concentrate and ask again.")
			.add("Don't count on it.", "My reply is no.", "My sources say no.", "Outlook not so good.", "Very doubtful.")
			.build();
	public static final Preset RUDE = null;
	public static final Preset OMINOUS = null;
	public static final Preset CONFIDENT = null;
	public static final Preset PESSIMISTIC = null;
	public static final Preset OPTIMISTIC = null;
	public static final Preset PARANOID = null;
	public static final Preset D20 = null;
	public static final Preset SMALL_MAN_TRAPPED_IN_BALL = null;

	public record Preset(String name, List<Component> responses, Map<String, String> lang) {
		public Preset(String name, List<Component> responses, Map<String, String> lang) {
			this.name = name;
			this.responses = responses;
			this.lang = lang;
			if (responses.size() != IcosahedronItem.FACES) {
				throw new RuntimeException("Invalid preset: " + responses);
			}
		}

		public record Builder(String name, List<Component> responses, Map<String, String> lang) {
			public Builder(String name) {
				this(name, new ArrayList<>(), new HashMap<>());
			}

			public Builder add(String... englishTranslations) {
				for (String str : englishTranslations) {
					this.add(str);
				}
				return this;
			}

			public Builder add(String englishTranslation) {
				String key = englishTranslation.toLowerCase(Locale.ROOT)
						.replace(' ', '_')
						.replaceAll("[,.']", "");
				return this.add(key, englishTranslation);
			}

			public Builder add(String responseKey, String englishTranslation) {
				String fullKey = "magic_8_ball.response." + this.name + "." + responseKey;
				Component component = Component.translatable(fullKey);
				this.responses.add(component);
				this.lang.put(fullKey, englishTranslation);
				return this;
			}

			public Preset build() {
				return new Preset(this.name, this.responses, this.lang);
			}
		}
	}
}

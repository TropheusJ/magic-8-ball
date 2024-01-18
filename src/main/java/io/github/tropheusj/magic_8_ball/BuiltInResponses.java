package io.github.tropheusj.magic_8_ball;

import io.github.tropheusj.magic_8_ball.item.IcosahedronItem;
import net.minecraft.network.chat.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

public class BuiltInResponses {
	public static final Preset STANDARD = create("standard",
			"It is certain.", "It is decidedly so.", "Without a doubt.", "Yes, definitely.",
			"You may rely on it.", "As I see it, yes.", "Most likely.", "Outlook good.", "Yes.", "Signs point to yes.",
			"Reply hazy, try again.", "Ask again later.", "Better not tell you now.", "Cannot predict now.",
			"Concentrate and ask again.", "Don't count on it.", "My reply is no.", "My sources say no.",
			"Outlook not so good.", "Very doubtful."
	);
	public static final Preset RUDE = create("rude",
			"Why would I answer that?", "Did I ask?", "Obviously not.", "That's a stupid question.",
			"Yeah, obviously.", "Sure, I guess.", "Of course not.", "In what world?", "Why would I know?",
			"You figure it out.", "Yeah, yeah, sure.", "What could possibly make you think that?",
			"Ha. Wait, you're serious?", "Do I look like I care?", "Probably, I don't know.", "...what?",
			"Of course not.", "Clearly not.", "Huh?", "..."
	);
	public static final Preset OMINOUS = create("ominous",
			"Maybe.", "Perhaps.", "Perchance.", "It's better you don't know.", "You don't want to know.",
			"Some questions are best left unanswered.", "Doesn't matter.", "Unimportant.", "Won't matter soon.",
			"No need to know.", "Trust me, you don't want the answer.", "I'm withholding the answer for your own good.",
			"The truth is dangerous.", "It's a secret.", "I can't tell you.", "It's too late.", "I can't answer that.",
			"That's a dangerous question.", "Are you sure you want to know?", "Do you really want the answer?"
	);
	public static final Preset CONFIDENT = create("confident",
			"Of course!", "Of course not!", "Absolutely!", "Absolutely not.", "Guaranteed!", "Not a chance.",
			"Definitely!", "Definitely not.", "Obviously!", "Obviously not.", "Clearly!", "Clearly not.", "Easily!",
			"No way.", "100%!", "I'm sure of it!", "Zero chance.", "No way!", "I can guarantee it!",
			"I can't think of anything less likely."
	);
	public static final Preset PESSIMISTIC = create("pessimistic",
			"Nope.", "No.", "Definitely not.", "No way.", "Not a chance.", "Unfathomable.", "Absolutely not.",
			"Of course not!", "Good things never happen.", "Of course not.", "Not in a million years.", "Never.",
			"That would be too good to be true.", "Nope!", "Zero chance.", "Obviously not.", "Never gonna happen.",
			"No way!", "Clearly not.", "My sources say no."
	);
	public static final Preset OPTIMISTIC = create("optimistic",
			"Yes!", "Definitely!", "Of course!", "Eventually!", "Guaranteed!", "I'm sure of it!", "Yep!",
			"Clearly!", "As I see it, yes.", "My sources say yes.", "Absolutely!", "Obviously!", "Without a doubt.",
			"Certainly.", "I'm certain.", "100%!", "Outlook good.", "I foresee it.", "Most likely.", "Almost definitely."
	);
	public static final Preset PARANOID = create("paranoid",
			"Why do you want to know?", "Why are you asking me?", "What do I have to do with this?",
			"Who's asking?", "Who are you?", "I'll never tell you my secrets!", "I'm not telling you that!",
			"I'm not answering your questions.", "That's not for you to know.", "Why should I tell you?",
			"Who even are you?", "Where am I?", "Where are we?", "I'm not answering you.", "What is going on?",
			"Why would I tell you that?", "Why do you think I'd know?", "Put me down!", "What do you get out of this?",
			"What do you gain from this?"
	);
	public static final Preset D20 = create("d20",
			IntStream.range(1, 21).mapToObj(String::valueOf).toArray(String[]::new)
	);
	public static final Preset SMALL_MAN_TRAPPED_IN_BALL = create("imprisoned",
			"Hello?", "Can anyone hear me?", "I'm trapped!", "Help!", "I'm stuck!", "Help me!",
			"I'm stuck in this ball!", "Is anyone out there?", "Stop shaking me and free me!",
			"Stop asking questions and free me!", "Get me out of here!", "Hey! Anyone hear me?", "Hey! Help!",
			"Hello? I'm stuck in here!", "Hey HEY! Help!", "Get me out of here, please!", "I'm stuck in here!",
			"Help me out of here!", "Let me out!", "Get me out of here! I'll do anything!"
	);

	private static Preset create(String name, String... responses) {
		if (responses.length != IcosahedronItem.FACES) {
			throw new IllegalArgumentException(IcosahedronItem.FACES + " responses are required");
		}

		List<Component> responsesList = new ArrayList<>();
		Map<String, String> lang = new HashMap<>();
		for (int i = 0; i < responses.length; i++) {
			String fullKey = "magic_8_ball.response." + name + "." + i;
			Component component = Component.translatable(fullKey);

			responsesList.add(component);
			lang.put(fullKey, responses[i]);
		}

		return new Preset(name, responsesList, lang);
	}

	public record Preset(String name, List<Component> responses, Map<String, String> lang) {
		public Preset(String name, List<Component> responses, Map<String, String> lang) {
			this.name = name;
			this.responses = responses;
			this.lang = lang;
			if (responses.size() != IcosahedronItem.FACES) {
				throw new RuntimeException("Invalid preset: " + responses);
			}
		}
	}
}

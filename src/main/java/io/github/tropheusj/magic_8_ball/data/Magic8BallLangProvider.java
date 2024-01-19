package io.github.tropheusj.magic_8_ball.data;

import io.github.tropheusj.magic_8_ball.BuiltInResponses;
import io.github.tropheusj.magic_8_ball.Magic8Ball;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;

public class Magic8BallLangProvider extends FabricLanguageProvider {
	protected Magic8BallLangProvider(FabricDataOutput dataOutput) {
		super(dataOutput);
	}

	@Override
	public void generateTranslations(TranslationBuilder builder) {
		// other lang, can't have both generated and non-generated
		builder.add(Magic8Ball.MAGIC_8_BALL, "Magic 8 Ball");
		builder.add(Magic8Ball.HEMISPHERE, "Hemisphere");
		builder.add(Magic8Ball.ICOSAHEDRON, "Icosahedron");
		builder.add(Magic8Ball.INCOMPLETE_ICOSAHEDRON, "Incomplete Icosahedron");
		builder.add(Magic8Ball.ICOSAHEDRON_FACE, "Icosahedron Face");

		builder.add("icosahedron.incomplete.faces", "Faces: %1$s / %2$s");
		builder.add("magic_8_ball.response", "The Magic 8 Ball says: %s");
		builder.add("magic_8_ball.no_response", "The Magic 8 Ball has nothing to say on the matter.");

		builder.add("subtitles.magic_8_ball.ball_shake", "Magic 8 Ball shakes");
		// 8-ball presets
		buildPreset(builder, BuiltInResponses.STANDARD);
		buildPreset(builder, BuiltInResponses.RUDE);
		buildPreset(builder, BuiltInResponses.OMINOUS);
		buildPreset(builder, BuiltInResponses.CONFIDENT);
		buildPreset(builder, BuiltInResponses.PESSIMISTIC);
		buildPreset(builder, BuiltInResponses.OPTIMISTIC);
		buildPreset(builder, BuiltInResponses.PARANOID);
		buildPreset(builder, BuiltInResponses.D20);
		buildPreset(builder, BuiltInResponses.SMALL_MAN_TRAPPED_IN_BALL);
		buildPreset(builder, BuiltInResponses.SMALL_SALESMAN_TRAPPED_IN_BALL);
	}

	protected void buildPreset(TranslationBuilder builder, BuiltInResponses.Preset preset) {
		preset.lang().forEach(builder::add);
	}
}

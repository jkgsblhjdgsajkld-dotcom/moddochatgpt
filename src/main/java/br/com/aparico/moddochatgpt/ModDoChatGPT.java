
package br.com.aparico.moddochatgpt;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.math.random.Random;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class ModDoChatGPT implements ModInitializer {
    public static final String MOD_ID = "moddochatgpt";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        ServerTickEvents.END_SERVER_TICK.register(server -> {
            for (ServerPlayerEntity player : server.getPlayerManager().getPlayerList()) {
                Random random = player.getRandom();

                if (random.nextInt(12_000) != 0 || player.isSpectator() || !player.isAlive()) {
                    continue;
                }

                int choice = random.nextInt(5);
                switch (choice) {
                    case 0 -> player.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 20 * 20, 0));
                    case 1 -> player.addStatusEffect(new StatusEffectInstance(StatusEffects.HASTE, 20 * 20, 0));
                    case 2 -> player.addStatusEffect(new StatusEffectInstance(StatusEffects.JUMP_BOOST, 20 * 20, 1));
                    case 3 -> player.addStatusEffect(new StatusEffectInstance(StatusEffects.NIGHT_VISION, 30 * 20, 0));
                    default -> player.addStatusEffect(new StatusEffectInstance(StatusEffects.LUCK, 45 * 20, 0));
                }

                player.sendMessage(Text.literal("§d✦ O mundo ainda guarda surpresas para você. ✦"), true);
            }
        });

        LOGGER.info("Mod do ChatGPT carregado.");
    }
}

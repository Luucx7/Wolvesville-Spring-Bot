package dev.luucx7.seitabot.discord.commands;

import dev.luucx7.seitabot.controllers.WolvesvilleController;
import dev.luucx7.seitabot.model.IDiscordCommand;
import org.javacord.api.DiscordApi;
import org.javacord.api.entity.message.MessageBuilder;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.event.interaction.SlashCommandCreateEvent;
import org.javacord.api.interaction.SlashCommand;
import org.javacord.api.interaction.callback.InteractionOriginalResponseUpdater;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.awt.*;

@Configuration
public class ToggleAutomaticSkipCommand implements IDiscordCommand {

    private final String COMANDO = "autoSkip";

    private final WolvesvilleController wovController;

    @Autowired
    public ToggleAutomaticSkipCommand(WolvesvilleController wolvesvilleController) {
        this.wovController = wolvesvilleController;
    }

    @Override
    public void register(DiscordApi api) {
        SlashCommand command = SlashCommand.with("autoskip", "Altera o modo de skip automático.")
                .createGlobal(api)
                .join();

        api.addSlashCommandCreateListener(this::respond);
    }

    private void respond(SlashCommandCreateEvent event) {
        if (event.getSlashCommandInteraction().getFullCommandName().equalsIgnoreCase(COMANDO)) {
            wovController.setAutomaticSkipWaitingTime(!wovController.isAutomaticSkipWaitingTime());

            EmbedBuilder embedBuilder = new EmbedBuilder()
                    .setTitle("Controle de skip automático");

            if (wovController.isAutomaticSkipWaitingTime()) {
                embedBuilder.setDescription("O skip automático do tempo de espera das missões foi ATIVADO.");
                embedBuilder.setColor(Color.GREEN);
            } else {
                embedBuilder.setDescription("O skip automático do tempo de espera das missões foi DESATIVADO.");
                embedBuilder.setColor(Color.RED);
            }

            InteractionOriginalResponseUpdater response = event.getSlashCommandInteraction().createImmediateResponder()
                    .addEmbed(embedBuilder)
                    .respond()
                    .join();
        }
    }
}

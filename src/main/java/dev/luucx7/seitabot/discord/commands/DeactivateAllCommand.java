package dev.luucx7.seitabot.discord.commands;

import dev.luucx7.seitabot.SeitaBotApplication;
import dev.luucx7.seitabot.controllers.WolvesvilleController;
import dev.luucx7.seitabot.model.IDiscordCommand;
import org.javacord.api.DiscordApi;
import org.javacord.api.event.interaction.SlashCommandCreateEvent;
import org.javacord.api.interaction.SlashCommand;
import org.javacord.api.interaction.callback.InteractionOriginalResponseUpdater;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DeactivateAllCommand implements IDiscordCommand {

    private final String COMANDO = "desativarTodos";

    private final WolvesvilleController wolvesvilleController;

    @Autowired
    public DeactivateAllCommand(ApplicationContext applicationContext, WolvesvilleController wolvesvilleController) {
        this.wolvesvilleController = wolvesvilleController;
    }

    @Override
    public void register(DiscordApi api) {
        SlashCommand command = SlashCommand.with("desativartodos", "Desativa todos os membros do clã.")
                .createGlobal(api)
                .join();

        api.addSlashCommandCreateListener((this::respond));
    }

    private void respond(SlashCommandCreateEvent event) {
        if (event.getSlashCommandInteraction().getFullCommandName().equalsIgnoreCase(COMANDO)) {
            InteractionOriginalResponseUpdater response = event.getSlashCommandInteraction().createImmediateResponder()
                            .append("Desativando todos os membros, por favor aguarde...")
                            .respond()
                            .join();

            wolvesvilleController.deactivateAllMembers();

            response.setContent("Todos os membros foram desativados.").update();
        }
    }
}

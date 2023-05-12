package dev.luucx7.seitabot.discord.commands;

import dev.luucx7.seitabot.controllers.WolvesvilleController;
import dev.luucx7.seitabot.model.IDiscordCommand;
import org.javacord.api.DiscordApi;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.event.interaction.SlashCommandCreateEvent;
import org.javacord.api.interaction.*;
import org.javacord.api.interaction.callback.InteractionOriginalResponseUpdater;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Configuration
public class DeactivateMemberCommand implements IDiscordCommand {

    private final String COMANDO = "desativar";
    private final String PLAYER_ARG = "jogador";

    private final WolvesvilleController wolvesvilleController;

    @Autowired
    public DeactivateMemberCommand(WolvesvilleController wolvesvilleController) {
        this.wolvesvilleController = wolvesvilleController;
    }

    @Override
    public void register(DiscordApi api) {
        List<SlashCommandOption> options = new LinkedList<>();
        SlashCommandOption playerOption = SlashCommandOption.create(SlashCommandOptionType.STRING, PLAYER_ARG, "Nome do jogador a ser desativado das missões.", true);
        options.add(playerOption);

        SlashCommand command = SlashCommand.with(COMANDO, "Desativa um membro do clã pelo nome, podendo ser o líder ou co-lideres.", options)
                .createGlobal(api)
                .join();

        api.addSlashCommandCreateListener((this::respond));
    }

    private void respond(SlashCommandCreateEvent event) {
        if (event.getSlashCommandInteraction().getFullCommandName().equalsIgnoreCase(COMANDO)) {
            InteractionOriginalResponseUpdater laterInteraction = event.getSlashCommandInteraction().respondLater().join();

            Optional<SlashCommandInteractionOption> optionOp = event.getSlashCommandInteraction().getArguments().stream().filter((arg) -> arg.getName().equalsIgnoreCase(PLAYER_ARG)).findAny();

            EmbedBuilder builder = new EmbedBuilder()
                    .setTitle("Desativação de membro");

            if (optionOp.isEmpty() || optionOp.get().getStringValue().isEmpty()) {
                builder.setColor(Color.RED).setDescription("Erro ao processar comando. Você inseriu corretamente o nome do jogador?");

                laterInteraction.addEmbed(builder).update().join();
                return;
            }

            SlashCommandInteractionOption option = optionOp.get();
            String playerName = option.getStringValue().get();

            boolean result = wolvesvilleController.changeParticipateInQuestsByName(playerName, false);

            if (result) {
                builder.setTitle("Desativação de membro")
                        .setDescription("O membro " + playerName + " foi desativado.")
                        .setColor(Color.GREEN);
            } else {
                builder.setTitle("Desativação de membro")
                        .setDescription("Ocorreu um erro ao desativar " + playerName + "\nVocê digitou o nome corretamente?")
                        .setColor(Color.RED);
            }

            if (playerName.equals("ContadorDeBatata")) {
                builder.setThumbnail(OSAKA);
            }

            laterInteraction.addEmbed(builder).update().join();
        }
    }

    private final String OSAKA = "https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Fvignette1.wikia.nocookie.net%2Fanimefanon%2Fimages%2F3%2F37%2FOsaka_%2528Azumanga_Daioh%2529.jpg%2Frevision%2Flatest%3Fcb%3D20131225090831&f=1&nofb=1&ipt=57fafa60815bf5fb040329cbdf9c28588f5d50524d781ad69be25566a71dfdf0&ipo=images";
}

package dev.luucx7.seitabot.beans;

import dev.luucx7.seitabot.configuration.DiscordConfiguration;
import dev.luucx7.seitabot.discord.commands.DeactivateAllCommand;
import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DiscordBotBean {

    private final DiscordConfiguration discordConfiguration;

    private final DeactivateAllCommand deactivateAllCommand;

    @Autowired
    public DiscordBotBean(DiscordConfiguration discordConfiguration, DeactivateAllCommand deactivateAllCommand) {
        this.discordConfiguration = discordConfiguration;
        this.deactivateAllCommand = deactivateAllCommand;
    }

    @Bean
    public DiscordApi gatewayDiscordClient() {
        DiscordApi api = new DiscordApiBuilder().setToken(discordConfiguration.getToken())
                .setAllIntents()
                .login()
                .join();

       deactivateAllCommand.register(api);


//        SlashCommand.with("skip", "Teste").createGlobal(api).join();
//        api.addSlashCommandCreateListener(event -> {
//            event.getSlashCommandInteraction().createImmediateResponder().append("teste").respond();
//        });
//
//        api.addMessageCreateListener(event -> {
//            if (event.getMessageContent().equalsIgnoreCase("!skip")) {
//                event.getChannel().sendMessage("eae!");
//            }
//        });

        return api;
    }
}

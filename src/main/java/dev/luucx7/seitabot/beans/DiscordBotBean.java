package dev.luucx7.seitabot.beans;

import dev.luucx7.seitabot.configuration.DiscordConfiguration;
import dev.luucx7.seitabot.discord.commands.AutomaticSkipStatusCommand;
import dev.luucx7.seitabot.discord.commands.DeactivateAllCommand;
import dev.luucx7.seitabot.discord.commands.ToggleAutomaticSkipCommand;
import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DiscordBotBean {

    private final DiscordConfiguration discordConfiguration;

    @Autowired
    private DeactivateAllCommand deactivateAllCommand;
    @Autowired
    private ToggleAutomaticSkipCommand toggleAutomaticSkipCommand;
    @Autowired
    private AutomaticSkipStatusCommand automaticSkipStatusCommand;

    @Autowired
    public DiscordBotBean(DiscordConfiguration discordConfiguration) {
        this.discordConfiguration = discordConfiguration;
    }

    @Bean
    public DiscordApi gatewayDiscordClient() {
        DiscordApi api = new DiscordApiBuilder().setToken(discordConfiguration.getToken())
                .setAllIntents()
                .login()
                .join();

        deactivateAllCommand.register(api);
        toggleAutomaticSkipCommand.register(api);
        automaticSkipStatusCommand.register(api);

        return api;
    }
}

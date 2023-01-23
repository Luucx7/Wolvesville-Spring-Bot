package dev.luucx7.seitabot.model;

import org.javacord.api.DiscordApi;

public interface IDiscordCommand {
    void register(DiscordApi api);
}

package dev.luucx7.seitabot.model;

import org.javacord.api.DiscordApi;

// TODO: a better way to handle this
public interface IDiscordCommand {
    void register(DiscordApi api);
}

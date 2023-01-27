package dev.luucx7.seitabot.wolvesville.model.quests;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

public class QuestParticipant {
    @Getter
    @Setter
    private UUID playerId;

    @Getter
    @Setter
    private String username;

    @Getter
    @Setter
    private int xp;
}

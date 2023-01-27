package dev.luucx7.seitabot.wolvesville.model.quests;

import lombok.Getter;
import lombok.Setter;

public class QuestReward {
    @Getter
    @Setter
    private String type;

    @Getter
    @Setter
    private int amount;

    @Getter
    @Setter
    private String avatarItemId;

    @Getter
    @Setter
    private String displayType;
}

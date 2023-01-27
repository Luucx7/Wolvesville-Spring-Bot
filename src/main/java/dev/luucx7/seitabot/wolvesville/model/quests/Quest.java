package dev.luucx7.seitabot.wolvesville.model.quests;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

public class Quest {
    @Getter
    @Setter
    private UUID id;

    @Getter
    @Setter
    private List<QuestReward> rewards;

    @Getter
    @Setter
    private String promoImageUrl;

    @Getter
    @Setter
    private String promoImagePrimaryColor;

    @Getter
    @Setter
    private boolean purchasableWithGems;
}

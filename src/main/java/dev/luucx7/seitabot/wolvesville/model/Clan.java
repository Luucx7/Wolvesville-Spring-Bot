package dev.luucx7.seitabot.wolvesville.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

public class Clan {
    @Getter
    @Setter
    private UUID id;

    @Getter
    @Setter
    private LocalDateTime creationTime;

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private String description;

    @Getter
    @Setter
    private int xp;

    @Getter
    @Setter
    private String language;

    @Getter
    @Setter
    private String icon;

    @Getter
    @Setter
    private String iconColor;

    @Getter
    @Setter
    private String tag;

    @Getter
    @Setter
    private String joinType;

    @Getter
    @Setter
    private UUID leaderId;

    @Getter
    @Setter
    private int questHistoryCount;

    @Getter
    @Setter
    private int minLevel;

    @Getter
    @Setter
    private int memberCount;

    @Getter
    @Setter
    private int gold;

    @Getter
    @Setter
    private int gems;
}

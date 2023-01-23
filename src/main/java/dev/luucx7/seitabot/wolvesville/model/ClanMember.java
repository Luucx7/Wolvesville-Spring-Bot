package dev.luucx7.seitabot.wolvesville.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

public class ClanMember {

    @Getter
    @Setter
    private UUID playerId;

    @Getter
    @Setter
    private String username;

    @Getter
    @Setter
    private LocalDateTime creationTime;

    @Getter
    @Setter
    private int xp;

    @Getter
    @Setter
    private String status;

    @Getter
    @Setter
    private boolean isCoLeader;

    @Getter
    @Setter
    private int level;

    @Getter
    @Setter
    private LocalDateTime lastOnline;

    @Getter
    @Setter
    private UUID profileIconId;

    @Getter
    @Setter
    private String profileIconColor;

    @Getter
    @Setter
    private String playerStatus;

    @Getter
    @Setter
    private String joinMessage;

    @Getter
    @Setter
    private boolean participateInClanQuests;
}

package dev.luucx7.seitabot.wolvesville.model.quests;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

public class ActiveQuest {

    @Getter
    @Setter
    private Quest quest;

    @Getter
    @Setter
    private int xp;

    @Getter
    @Setter
    private int xpPerReward;

    @Getter
    @Setter
    private int tier;

    @Getter
    @Setter
    private LocalDateTime tierStartTime;

    @Getter
    @Setter
    private List<QuestParticipant> participants;

    @Getter
    @Setter
    private boolean tierFinished;

    @Getter
    @Setter
    private boolean claimedTime;

    @Getter
    @Setter
    private LocalDateTime tierEndTime;

}

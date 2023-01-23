package dev.luucx7.seitabot.wolvesville.model.requests;

import lombok.Getter;
import lombok.Setter;

public class ActiveInQuestBody {

    @Getter
    private boolean participateInQuests;

    public ActiveInQuestBody setParticipateInQuests(boolean participateInQuests) {
        this.participateInQuests = participateInQuests;

        return this;
    }
}

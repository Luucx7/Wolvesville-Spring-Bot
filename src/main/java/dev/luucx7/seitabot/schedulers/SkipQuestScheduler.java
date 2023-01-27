package dev.luucx7.seitabot.schedulers;

import dev.luucx7.seitabot.wolvesville.WolvesvilleAPI;
import dev.luucx7.seitabot.wolvesville.model.quests.ActiveQuest;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class SkipQuestScheduler {

    private final WolvesvilleAPI wolvesvilleAPI;

    public SkipQuestScheduler(WolvesvilleAPI api) {
        this.wolvesvilleAPI = api;
    }

    @Scheduled(fixedDelay = 120000, initialDelay = 5000)
    public void lookForSkip() {
        ActiveQuest quest = wolvesvilleAPI.getClanActiveQuest();

        if (!(quest == null)) {
            if (quest.isTierFinished()) wolvesvilleAPI.skipWaitingTime();
        }
    }
}

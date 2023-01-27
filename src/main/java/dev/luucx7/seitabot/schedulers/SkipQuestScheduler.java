package dev.luucx7.seitabot.schedulers;

import dev.luucx7.seitabot.controllers.WolvesvilleController;
import dev.luucx7.seitabot.wolvesville.model.quests.ActiveQuest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class SkipQuestScheduler {

    private final WolvesvilleController wovController;

    @Autowired
    public SkipQuestScheduler(WolvesvilleController controller) {
        this.wovController = controller;
    }

    @Scheduled(fixedDelay = 60000, initialDelay = 1000)
    public void lookForSkip() {
        try {
            if (!wovController.isAutomaticSkipWaitingTime()) return;

            ActiveQuest quest = wovController.getApi().getClanActiveQuest();

            if (!(quest == null)) {
                if (quest.isTierFinished()) wovController.getApi().skipWaitingTime();
            }
        } catch(Exception e) {
            boolean a = true;
        }
    }
}

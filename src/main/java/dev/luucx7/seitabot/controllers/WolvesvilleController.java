package dev.luucx7.seitabot.controllers;

import dev.luucx7.seitabot.wolvesville.WolvesvilleAPI;
import dev.luucx7.seitabot.wolvesville.model.ClanMember;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
public class WolvesvilleController {

    @Getter(AccessLevel.PUBLIC)
    private final WolvesvilleAPI api;

    @Getter(AccessLevel.PUBLIC)
    @Setter(AccessLevel.PUBLIC)
    private boolean automaticSkipWaitingTime = false;

    public WolvesvilleController(WolvesvilleAPI api) {
        this.api = api;
    }

    @Async
    public void deactivateAllMembers() {
        List<ClanMember> members = getApi().getClanMembers();
        List<ClanMember> activeMembers = members.stream().filter(ClanMember::isParticipateInClanQuests).toList();

        for (ClanMember member : activeMembers) {
            try {
                ClanMember returnedMember = api.setParticipateInQuests(member, false);
                member.setParticipateInClanQuests(returnedMember.isParticipateInClanQuests());
                Thread.sleep(1000);
            } catch(IOException ex) {
                // TODO: handle with failed requests
            } catch(InterruptedException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}

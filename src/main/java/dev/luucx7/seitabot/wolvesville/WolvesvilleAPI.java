package dev.luucx7.seitabot.wolvesville;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.sun.istack.NotNull;
import dev.luucx7.seitabot.core.request.ApiConsumer;
import dev.luucx7.seitabot.core.request.RequestProtocol;
import dev.luucx7.seitabot.core.request.adapters.LocalDateTimeAdapter;
import dev.luucx7.seitabot.wolvesville.model.Clan;
import dev.luucx7.seitabot.wolvesville.model.ClanMember;
import dev.luucx7.seitabot.wolvesville.model.quests.ActiveQuest;
import dev.luucx7.seitabot.wolvesville.model.requests.ActiveInQuestBody;
import lombok.AccessLevel;
import lombok.Getter;
import org.springframework.lang.Nullable;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class WolvesvilleAPI {
    public static final String WOV_URI = "https://api.wolvesville.com";
//    public static final int CACHE_DELAY = 300000;

    @Getter(AccessLevel.PRIVATE)
    private final String token;
    @Getter
    private final String clanId;

    @Getter(AccessLevel.PROTECTED)
    private final ApiConsumer consumer;

    public WolvesvilleAPI(String token, String clanId) {
        this.token = token;
        this.clanId = clanId;

        HashMap<String, String> baseHeaders = new HashMap<>();
        baseHeaders.put("Accept", "application/json");
        baseHeaders.put("Content-Type", "application/json");

        this.consumer = new ApiConsumer(WOV_URI, baseHeaders);
        this.consumer.setAuthorization("Bot " + this.getToken());
    }

    public Clan getClan() {
        try {
            String endpoint = "/clans/" + getClanId() + "/info";

            return consumer.fetchAndResolve(Clan.class, endpoint, RequestProtocol.GET, "", 10);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        } catch (URISyntaxException e) {
            throw new RuntimeException("Error while parsing WOV URI: Try update the app.", e);
        }
    }

    public List<ClanMember> getClanMembers() {
        try {
            String endpoint = "/clans/" + getClanId() + "/members";

            Type listType = new TypeToken<List<ClanMember>>(){}.getType();

            return consumer.fetchAndResolve(listType, endpoint, RequestProtocol.GET, "", 10);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        } catch (URISyntaxException e) {
            throw new RuntimeException("Error while parsing WOV URI: Try update the app.", e);
        }
    }

    @Nullable
    public ActiveQuest getClanActiveQuest() {
        try {
            String endpoint = "/clans/" + getClanId() + "/quests/active";

            String response = consumer.fetch(endpoint, RequestProtocol.GET, "", 10);

            if (response.contains("HTTP 404 Not Found")) {
                return null;
            } else {
                GsonBuilder gsonBuilder = new GsonBuilder();
                gsonBuilder.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter());
                Gson gson = gsonBuilder.create();

                return gson.fromJson(response, ActiveQuest.class);
            }
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        } catch (URISyntaxException e) {
            throw new RuntimeException("Error while parsing WOV URI: Try update the app.", e);
        }
    }

    public boolean skipWaitingTime() {
        try {
            String endpoint = "/clans/" + getClanId() + "/quests/active/skipWaitingTime";

            String response = consumer.fetch(endpoint, RequestProtocol.POST, "", 10);

            return !response.contains("HTTP 404 Not Found");
        } catch (IOException e) {
            // TODO: check if 404
            return false;
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (URISyntaxException e) {
            throw new RuntimeException("Error while parsing WOV URI: Try update the app.", e);
        }
    }

    @Nullable
    public ClanMember getClanMember(UUID uuid) {
        try {
            String endpoint = "/clans/" + getClanId() + "/members/" + uuid;

            return consumer.fetchAndResolve(ClanMember.class, endpoint, RequestProtocol.GET, "", 10);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        } catch (URISyntaxException e) {
            throw new RuntimeException("Error while parsing WOV URI: Try update the app.", e);
        }
    }

    public ClanMember setParticipateInQuests(@NotNull ClanMember member, boolean participateInQuests) throws IOException {
        return this.setParticipateInQuests(member.getPlayerId(), participateInQuests);
    }
    public ClanMember setParticipateInQuests(@NotNull UUID memberID, boolean participateInQuests) throws IOException {
        try {
            String endpoint = "/clans/" + getClanId() + "/members/" + memberID + "/participateInQuests";

            ActiveInQuestBody bodyObject = new ActiveInQuestBody().setParticipateInQuests(participateInQuests);
            String body = new Gson().toJson(bodyObject);

            return consumer.fetchAndResolve(ClanMember.class, endpoint, RequestProtocol.PUT, body, 10);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (URISyntaxException e) {
            throw new RuntimeException("Error while parsing WOV URI: Try update the app.", e);
        }
    }
}

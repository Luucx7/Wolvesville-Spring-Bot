package dev.luucx7.seitabot.core.request;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import com.sun.istack.NotNull;
import dev.luucx7.seitabot.core.request.adapters.LocalDateTimeAdapter;
import lombok.Getter;
import org.javacord.api.util.auth.Request;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class ApiConsumer {

    @Getter
    private final String baseURI;
    @Getter
    private final HashMap<String, String> baseHeaders;

    public ApiConsumer(String baseURI) {
        if (baseURI.endsWith("/")) baseURI = baseURI.substring(0, baseURI.length());
        this.baseURI = baseURI;
        this.baseHeaders = new HashMap<String, String>();
    }

    public ApiConsumer(String baseURI, HashMap<String, String> baseHeaders) {
        if (baseURI.endsWith("/")) baseURI = baseURI.substring(0, baseURI.length());
        this.baseURI = baseURI;
        this.baseHeaders = baseHeaders;
    }

    public ApiConsumer setAuthorization(final String value) {
        this.baseHeaders.put("Authorization", value);

        return this;
    }

    public String fetch(@NotNull String endpoint, RequestProtocol protocol, String body, int timeout) throws URISyntaxException, IOException, InterruptedException {
        if (protocol == null) protocol = RequestProtocol.GET;

        URI uri = new URI(baseURI + endpoint);
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest.Builder tempBuilder = null;

        switch (protocol) {
            case GET -> tempBuilder = getRequest();
            case POST -> tempBuilder = postRequest(body);
            case PUT -> tempBuilder = putRequest(body);
            case DELETE -> tempBuilder = deleteRequest();
            case HEAD -> tempBuilder = headRequest();
        }

        tempBuilder.uri(uri);

        final HttpRequest.Builder builder = tempBuilder;
        this.baseHeaders.forEach(builder::setHeader);

        HttpRequest request = builder.build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        return response.body();
    }

    public <T> T fetchAndResolve(Class<T> clazz, @NotNull String endpoint, RequestProtocol protocol, String body, int timeout) throws URISyntaxException, IOException, InterruptedException {
        String responseBody = this.fetch(endpoint, protocol, body, timeout);

        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter());
        Gson gson = gsonBuilder.create();

        return gson.fromJson(responseBody, clazz);
    }

    public <T> T fetchAndResolve(Type type, @NotNull String endpoint, RequestProtocol protocol, String body, int timeout) throws URISyntaxException, IOException, InterruptedException {
        String responseBody = this.fetch(endpoint, protocol, body, timeout);

        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter());
        Gson gson = gsonBuilder.create();

        return gson.fromJson(responseBody, type);
    }

    private HttpRequest.Builder getRequest() {
        return HttpRequest.newBuilder().GET();
    }

    private HttpRequest.Builder postRequest(String body) {
        return HttpRequest.newBuilder()
                .POST(getPublisher(body));
    }

    private HttpRequest.Builder putRequest(String body) {
        return HttpRequest.newBuilder()
                .PUT(getPublisher(body));
    }

    private HttpRequest.Builder deleteRequest() {
        return HttpRequest.newBuilder().DELETE();
    }

    private HttpRequest.Builder headRequest() {
        return HttpRequest.newBuilder().HEAD();
    }
    private HttpRequest.BodyPublisher getPublisher(String body) {
        if (body == null) return HttpRequest.BodyPublishers.noBody();

        return HttpRequest.BodyPublishers.ofString(body);
    }
}

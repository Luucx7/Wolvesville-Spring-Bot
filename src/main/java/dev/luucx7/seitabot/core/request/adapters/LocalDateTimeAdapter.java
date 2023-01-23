package dev.luucx7.seitabot.core.request.adapters;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class LocalDateTimeAdapter implements JsonDeserializer<LocalDateTime> {

    @Override
    public LocalDateTime deserialize(JsonElement json, Type typeOf, JsonDeserializationContext context) throws JsonParseException {
        ZonedDateTime zonedDateTime = ZonedDateTime.parse(json.getAsString()).withZoneSameInstant(ZoneId.of("America/Sao_Paulo"));
        return zonedDateTime.toLocalDateTime();
    }
}

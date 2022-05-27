package com.example.monolithic.global.serializer;

import com.example.monolithic.global.property.Format;
import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@JsonComponent
public class LocalDatetimeJsonDeserializer extends JsonDeserializer<LocalDateTime> {

    @Override
    public LocalDateTime deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        if (jsonParser.getText() != null || !jsonParser.getText().isBlank()) {
            return LocalDateTime.parse(jsonParser.getText(), DateTimeFormatter.ofPattern(Format.DATETIME));
        }
        return null;
    }
}

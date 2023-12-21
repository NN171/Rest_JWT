package com.server.SpringServer.data;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.springframework.boot.jackson.JsonComponent;

import java.time.LocalDateTime;

@JsonComponent
public class DateSerializer {  //Попытка внести в бд расписание миита через API миита (не реализовано)
    public static class LocalDateTimeDeserializer extends JsonDeserializer<LocalDateTime>{
        @Override
        public LocalDateTime deserialize(JsonParser jsonParser, DeserializationContext deserializationContext){
            return null;
        }
    }
}

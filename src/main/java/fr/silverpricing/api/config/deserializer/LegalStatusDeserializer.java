package fr.silverpricing.api.config.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;
import fr.silverpricing.api.model.LegalStatus;

import java.io.IOException;

public class LegalStatusDeserializer extends JsonDeserializer<LegalStatus> {
    @Override
    public LegalStatus deserialize(JsonParser parser, DeserializationContext context)
            throws IOException, JsonProcessingException {
        return LegalStatus.fromString(parser.getValueAsString());
    }
}
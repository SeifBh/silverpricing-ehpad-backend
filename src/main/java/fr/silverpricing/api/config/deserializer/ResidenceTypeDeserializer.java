package fr.silverpricing.api.config.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import fr.silverpricing.api.model.LegalStatus;
import fr.silverpricing.api.model.ResidenceType;

import java.io.IOException;

public class ResidenceTypeDeserializer extends JsonDeserializer<ResidenceType> {
    @Override
    public ResidenceType deserialize(JsonParser parser, DeserializationContext context)
            throws IOException, JsonProcessingException {
        return ResidenceType.fromString(parser.getValueAsString());
    }
}
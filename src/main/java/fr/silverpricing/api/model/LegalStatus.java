package fr.silverpricing.api.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public enum LegalStatus {
    @JsonProperty("Privé non lucratif")
    PRIVÉ_NON_LUCRATIF,
    @JsonProperty("Privé commercial")
    PRIVÉ_COMMERCIAL,
    @JsonProperty("Public")
    PUBLIC;

    public static LegalStatus fromString(String value) {
        for (LegalStatus grade : values()) {
            if (grade.name().equalsIgnoreCase(value.replaceAll(" ", "_"))) {
                return grade;
            }
        }

        return PUBLIC;
    }

}

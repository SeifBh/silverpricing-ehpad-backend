package fr.silverpricing.api.model;

public enum ResidenceType {
    EHPAD,
    NOT_EHPAD,
    OTHER;
    public static ResidenceType fromString(String value) {
        for (ResidenceType grade : values()) {
            if (grade.name().equalsIgnoreCase(value.replaceAll(" ", "_"))) {
                return grade;
            }
        }

        return OTHER;
    }
}

package com.example.practicalfive;

/**
 * A regular helper class (not an Activity) responsible for validating and
 * normalising user input against the four Chinese direct-controlled municipalities.
 * Both MainActivity and Municipality reuse this logic so validation lives in one place.
 */
public class TextProcessor {

    /**
     * Accepts a raw municipality name, trims it and matches it case-insensitively.
     *
     * @param municipality the raw text entered by the user.
     * @return the correctly capitalised municipality name, or {@code null} when the
     *         input is null, blank, or not one of the four supported municipalities.
     */
    public static String processMunicipality(String municipality) {
        if (municipality == null) {
            return null;
        }

        String trimmed = municipality.trim();
        if (trimmed.isEmpty()) {
            return null;
        }

        switch (trimmed.toLowerCase()) {
            case "beijing":
                return "Beijing";
            case "shanghai":
                return "Shanghai";
            case "tianjin":
                return "Tianjin";
            case "chongqing":
                return "Chongqing";
            default:
                return null;
        }
    }
}

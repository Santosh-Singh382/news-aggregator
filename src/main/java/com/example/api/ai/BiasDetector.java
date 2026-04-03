package com.example.api.ai;

public class BiasDetector {

    // Detect bias using title + content
    public static String detectBias(String title, String content) {
        if (title == null && content == null) {
            return "Center";
        }

        String text = ((title != null ? title : "") + " " +
                       (content != null ? content : "")).toLowerCase();

        // LEFT bias indicators
        if (text.contains("government") ||
            text.contains("policy") ||
            text.contains("election") ||
            text.contains("rights") ||
            text.contains("welfare")) {
            return "Left";
        }

        // RIGHT bias indicators
        if (text.contains("market") ||
            text.contains("business") ||
            text.contains("economy") ||
            text.contains("investment") ||
            text.contains("trade")) {
            return "Right";
        }

        // Default
        return "Center";
    }
}

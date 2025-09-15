package com.example.api.ai;

public class BiasDetector {

    // Detect bias using title + content
    public static String detectBias(String title, String content) {
        if (title == null && content == null) return "Neutral";

        String text = (title != null ? title : "") + " " + (content != null ? content : "");
        text = text.toLowerCase();

        if (text.contains("government") || text.contains("politics")) {
            return "Political Bias";
        } else if (text.contains("market") || text.contains("business")) {
            return "Economic Bias";
        } else if (text.contains("sports") || text.contains("match")) {
            return "Sports Bias";
        }
        return "Neutral";
    }
}
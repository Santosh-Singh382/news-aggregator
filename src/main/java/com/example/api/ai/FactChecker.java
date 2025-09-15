package com.example.api.ai;

public class FactChecker {

    // Run fact-checking on content
    public static String checkFacts(String content) {
        if (content == null || content.isEmpty()) {
            return "No data to fact-check";
        }

        if (content.toLowerCase().contains("fake") || content.toLowerCase().contains("rumor")) {
            return "⚠️ Possibly Fake / Rumor";
        } else if (content.toLowerCase().contains("official") || content.toLowerCase().contains("verified")) {
            return "✅ Verified Source";
        }
        return "ℹ️ Not Verified";
    }
}
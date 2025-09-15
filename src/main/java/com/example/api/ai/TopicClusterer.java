package com.example.api.ai;

public class TopicClusterer {

    // Assign topic cluster using title + content
    public static String assignTopic(String title, String content) {
        if (title == null && content == null) return "General";

        String text = (title != null ? title : "") + " " + (content != null ? content : "");
        text = text.toLowerCase();

        if (text.contains("election") || text.contains("president")) {
            return "Politics";
        } else if (text.contains("stock") || text.contains("crypto")) {
            return "Finance";
        } else if (text.contains("football") || text.contains("cricket")) {
            return "Sports";
        } else if (text.contains("ai") || text.contains("technology")) {
            return "Technology";
        }
        return "General";
    }
}
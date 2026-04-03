package com.example.api.ai;

import com.example.api.entity.NewsEntity;
import java.util.Arrays;
import java.util.List;

public class NewsAIProcessor {

    // 🔹 Simple summarizer (shorten description/content)
    public static String summarize(String description, String content) {
        if (description != null && description.length() > 0) {
            return description.length() > 150 ? description.substring(0, 150) + "..." : description;
        } else if (content != null && content.length() > 0) {
            return content.length() > 150 ? content.substring(0, 150) + "..." : content;
        } else {
            return "No summary available.";
        }
    }

    // 🔹 Simple categorizer (keyword-based)
    public static String categorize(String title, String description) {
        String text = (title + " " + description).toLowerCase();

        List<String> politics = Arrays.asList("election", "president", "government", "policy");
        List<String> sports = Arrays.asList("match", "tournament", "goal", "cricket", "football");
        List<String> tech = Arrays.asList("technology", "ai", "software", "gadget", "startup");
        List<String> health = Arrays.asList("health", "covid", "vaccine", "medicine", "hospital");
        List<String> entertainment = Arrays.asList("movie", "film", "actor", "actress", "music");

        if (politics.stream().anyMatch(text::contains)) return "Politics";
        if (sports.stream().anyMatch(text::contains)) return "Sports";
        if (tech.stream().anyMatch(text::contains)) return "Technology";
        if (health.stream().anyMatch(text::contains)) return "Health";
        if (entertainment.stream().anyMatch(text::contains)) return "Entertainment";

        return "General";
    }

    // 🔹 Sentiment analysis (keyword-based for now)
    public static String analyzeSentiment(String text) {
        if (text == null || text.isEmpty()) {
            return "Neutral";
        }
        text = text.toLowerCase();

        List<String> positiveWords = Arrays.asList("good", "great", "excellent", "positive", "success", "happy", "win");
        List<String> negativeWords = Arrays.asList("bad", "poor", "negative", "loss", "fail", "sad", "violence");

        long positiveScore = positiveWords.stream().filter(text::contains).count();
        long negativeScore = negativeWords.stream().filter(text::contains).count();

        if (positiveScore > negativeScore) return "Positive";
        else if (negativeScore > positiveScore) return "Negative";
        else return "Neutral";
    }

    // 🔹 Process News Entity with AI
    public static void process(NewsEntity entity) {
        String summary = summarize(entity.getDescription(), entity.getContent());
        String category = categorize(entity.getTitle(), entity.getDescription());
        String sentiment = analyzeSentiment(entity.getTitle() + " " + entity.getDescription() + " " + entity.getContent());

        
        entity.setCategory(category);
        entity.setSentiment(sentiment);
    }
}

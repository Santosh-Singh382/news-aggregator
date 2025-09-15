package com.example.api.service;

import com.example.api.entity.NewsEntity;
import com.example.api.repository.NewsRepository;
import com.example.api.dto.NewsApiResponse;
import com.example.api.ai.NewsAIProcessor;
import com.example.api.ai.BiasDetector;
import com.example.api.ai.FactChecker;
import com.example.api.ai.TopicClusterer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NewsApiService {

    private static final String API_KEY = "ad04435223d14ddeafe52daadcf46ad2";

    // 🌍 Global news (worldwide, English only)
    private static final String WORLD_NEWS_URL =
            "https://newsapi.org/v2/everything?q=latest&language=en&sortBy=publishedAt&apiKey=" + API_KEY;

    @Autowired
    private NewsRepository newsRepository;

    @Autowired
    private RestTemplate restTemplate;

    // ✅ Manual Fetch (World News)
    public List<NewsEntity> fetchNews() {
        return fetchAndSaveNews(WORLD_NEWS_URL, "WORLD");
    }

    // ✅ Auto refresh every 2 minutes
    @Scheduled(fixedRate = 120000)
    public void autoUpdateNews() {
        System.out.println("🔄 Auto update triggered at: " + java.time.LocalDateTime.now());
        fetchNews();
    }

    // ✅ Common fetch method
    private List<NewsEntity> fetchAndSaveNews(String url, String category) {
        NewsApiResponse response = restTemplate.getForObject(url, NewsApiResponse.class);

        if (response == null || response.getArticles() == null) {
            throw new RuntimeException("No news found from API");
        }

        List<NewsEntity> entities = response.getArticles().stream()
                .map(article -> {
                    if (newsRepository.existsByUrl(article.getUrl())) {
                        return null; // Skip duplicates
                    }

                    NewsEntity entity = new NewsEntity();
                    entity.setAuthor(article.getAuthor());
                    entity.setTitle(article.getTitle());
                    entity.setDescription(article.getDescription());
                    entity.setUrl(article.getUrl());
                    entity.setUrlToImage(article.getUrlToImage());
                    entity.setPublishedAt(article.getPublishedAt());
                    entity.setContent(article.getContent());

                    // 🧠 AI Processing
                    NewsAIProcessor.process(entity);
                    entity.setBiasLabel(BiasDetector.detectBias(entity.getTitle(), entity.getContent()));
                    entity.setTopicCluster(TopicClusterer.assignTopic(entity.getTitle(), entity.getContent()));
                    entity.setFactCheck(FactChecker.checkFacts(entity.getContent()));

                    // ✅ Save as WORLD category
                    entity.setCategory(category);

                    return entity;
                })
                .filter(e -> e != null)
                .collect(Collectors.toList());

        if (!entities.isEmpty()) {
            newsRepository.saveAll(entities);
        }

        return entities;
    }

    // ✅ Search
    public List<NewsEntity> searchNews(String keyword) {
        return newsRepository.findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCase(keyword, keyword);
    }

    // ✅ Filter by sentiment
    public List<NewsEntity> filterBySentiment(String sentiment) {
        return newsRepository.findBySentimentIgnoreCase(sentiment);
    }

    // ✅ Filter by bias label
    public List<NewsEntity> filterByBias(String biasLabel) {
        return newsRepository.findByBiasLabelIgnoreCase(biasLabel);
    }

    // ✅ Filter by topic
    public List<NewsEntity> filterByTopic(String topicCluster) {
        return newsRepository.findByTopicClusterIgnoreCase(topicCluster);
    }

    // ✅ Filter by category (WORLD here)
    public List<NewsEntity> filterByCountry(String countryCode) {
        return newsRepository.findByCategoryIgnoreCase(countryCode);
    }
}

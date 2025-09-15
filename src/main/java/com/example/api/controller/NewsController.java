package com.example.api.controller;

import com.example.api.entity.NewsEntity;
import com.example.api.service.NewsApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/news")
public class NewsController {

    @Autowired
    private NewsApiService newsService;

    // ✅ Get all news from DB
    @GetMapping
    public List<NewsEntity> getAllNews() {
        return newsService.searchNews(""); // return everything
    }

    // ✅ Fetch live India + US news (save + return)
    @GetMapping("/live")
    public List<NewsEntity> fetchLiveNews() {
        return newsService.fetchNews(); // updated service method
    }

    // ✅ Search news by keyword
    @GetMapping("/search")
    public List<NewsEntity> searchNews(@RequestParam String keyword) {
        return newsService.searchNews(keyword);
    }

    // ✅ Filter by sentiment
    @GetMapping("/sentiment/{type}")
    public List<NewsEntity> filterBySentiment(@PathVariable String type) {
        return newsService.filterBySentiment(type);
    }

    // ✅ Filter by bias
    @GetMapping("/bias/{biasLabel}")
    public List<NewsEntity> filterByBias(@PathVariable String biasLabel) {
        return newsService.filterByBias(biasLabel);
    }

    // ✅ Filter by topic
    @GetMapping("/topic/{topicCluster}")
    public List<NewsEntity> filterByTopic(@PathVariable String topicCluster) {
        return newsService.filterByTopic(topicCluster);
    }

    // ✅ Filter by country (in / us)
    @GetMapping("/country/{countryCode}")
    public List<NewsEntity> filterByCountry(@PathVariable String countryCode) {
        return newsService.filterByCountry(countryCode);
    }
}

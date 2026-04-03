package com.example.api.repository;

import com.example.api.entity.NewsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NewsRepository extends JpaRepository<NewsEntity, Long> {

    boolean existsByUrl(String url);

    // Search
    List<NewsEntity> findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCase(
            String title, String description
    );

    // Sentiment filter
    List<NewsEntity> findBySentimentIgnoreCase(String sentiment);

    // ✅ BIAS FILTER (THIS WAS THE PROBLEM)
    List<NewsEntity> findByBiasLabelIgnoreCase(String biasLabel);

    // Topic filter
    List<NewsEntity> findByTopicClusterIgnoreCase(String topicCluster);

    // Category filter
    List<NewsEntity> findByCategoryIgnoreCase(String category);
}

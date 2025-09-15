package com.example.api.repository;

import com.example.api.entity.NewsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NewsRepository extends JpaRepository<NewsEntity, Long> {
    boolean existsByUrl(String url);

    List<NewsEntity> findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCase(String title, String desc);

    List<NewsEntity> findBySentimentIgnoreCase(String sentiment);

    List<NewsEntity> findByBiasLabelIgnoreCase(String biasLabel);

    List<NewsEntity> findByTopicClusterIgnoreCase(String topicCluster);

    List<NewsEntity> findByCategoryIgnoreCase(String category);
}

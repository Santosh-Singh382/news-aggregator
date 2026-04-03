package com.example.api.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "news")
public class NewsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String author;

    @Column(columnDefinition = "TEXT")
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    private String url;
   
    private String publishedAt;
    @Column(name = "url_to_image", length = 1000)
    private String urlToImage;


    @Lob
    private String content; // Keep @Lob because this can be very long

    @Column(name = "bias_label", columnDefinition = "TEXT")
    private String biasLabel;


    @Column(columnDefinition = "TEXT")
    private String topicCluster; // searchable, so TEXT

    @Lob
    private String factCheck; // can be long, not searchable

    private String category;
    private String sentiment;

    // Getters and Setters below
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getUrl() { return url; }
    public void setUrl(String url) { this.url = url; }

    public String getUrlToImage() { return urlToImage; }
    public void setUrlToImage(String urlToImage) { this.urlToImage = urlToImage; }

    public String getPublishedAt() { return publishedAt; }
    public void setPublishedAt(String publishedAt) { this.publishedAt = publishedAt; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public String getBiasLabel() { return biasLabel; }
    public void setBiasLabel(String biasLabel) { this.biasLabel = biasLabel; }

    public String getTopicCluster() { return topicCluster; }
    public void setTopicCluster(String topicCluster) { this.topicCluster = topicCluster; }

    public String getFactCheck() { return factCheck; }
    public void setFactCheck(String factCheck) { this.factCheck = factCheck; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public String getSentiment() { return sentiment; }
    public void setSentiment(String sentiment) { this.sentiment = sentiment; }
}

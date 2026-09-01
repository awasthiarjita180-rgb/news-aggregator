package com.newsaggregator.newsaggregator.recommendation;

import com.newsaggregator.newsaggregator.entity.*;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class RecommendationService {

    private final ArticleRepository articleRepository;
    private final ReadingHistoryRepository readingHistoryRepository;

    public RecommendationService(ArticleRepository articleRepository,
                                 ReadingHistoryRepository readingHistoryRepository) {
        this.articleRepository = articleRepository;
        this.readingHistoryRepository = readingHistoryRepository;
    }

    public List<Article> getRecommendations(String userId) {
        List<ReadingHistory> history = readingHistoryRepository.findByUserId(userId);

        if (history.isEmpty()) {
            // No history yet — fall back to most recent articles overall
            return articleRepository.findAll().stream()
                    .sorted(Comparator.comparing(Article::getPublishedAt).reversed())
                    .limit(10)
                    .collect(Collectors.toList());
        }

        Set<Long> readArticleIds = history.stream()
                .map(ReadingHistory::getArticleId)
                .collect(Collectors.toSet());

        // Count how often each category was read (behavioral signal)
        Map<String, Long> categoryFrequency = history.stream()
                .map(ReadingHistory::getArticleId)
                .map(articleRepository::findById)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.groupingBy(Article::getCategory, Collectors.counting()));

        List<String> topCategories = categoryFrequency.entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());

        // Content-based fetch: unread articles in the user's favorite categories
        return articleRepository.findByCategoryInOrderByPublishedAtDesc(topCategories).stream()
                .filter(article -> !readArticleIds.contains(article.getId()))
                .limit(10)
                .collect(Collectors.toList());
    }
}
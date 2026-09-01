package com.newsaggregator.newsaggregator.recommendation;

import com.newsaggregator.newsaggregator.entity.Article;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recommendations")
public class RecommendationController {

    private final RecommendationService recommendationService;

    public RecommendationController(RecommendationService recommendationService) {
        this.recommendationService = recommendationService;
    }

    @GetMapping("/{userId}")
    public List<Article> getRecommendations(@PathVariable String userId) {
        return recommendationService.getRecommendations(userId);
    }
}
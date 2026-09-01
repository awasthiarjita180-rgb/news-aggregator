package com.newsaggregator.newsaggregator.sentiment;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class SentimentController {

    private final SentimentService sentimentService;

    public SentimentController(SentimentService sentimentService) {
        this.sentimentService = sentimentService;
    }

    @PostMapping("/sentiment")
    public SentimentResponse analyzeSentiment(@RequestBody SentimentRequest request) {
        String sentiment = sentimentService.analyzeSentiment(request.text());
        return new SentimentResponse(sentiment);
    }

    public record SentimentRequest(String text) {}
    public record SentimentResponse(String sentiment) {}
}
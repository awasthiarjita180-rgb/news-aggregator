package com.newsaggregator.newsaggregator.sentiment;

import com.newsaggregator.newsaggregator.client.OpenAiClient;
import org.springframework.stereotype.Service;

@Service
public class SentimentService {

    private final OpenAiClient openAiClient;

    public SentimentService(OpenAiClient openAiClient) {
        this.openAiClient = openAiClient;
    }

    public String analyzeSentiment(String text) {
        if (text == null || text.isBlank()) {
            throw new IllegalArgumentException("Text must not be empty");
        }
        return openAiClient.analyzeSentiment(text);
    }
}
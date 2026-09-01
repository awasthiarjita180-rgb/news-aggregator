package com.newsaggregator.newsaggregator.summarization;

import com.newsaggregator.newsaggregator.client.OpenAiClient;
import org.springframework.stereotype.Service;

@Service
public class SummarizationService {

    private final OpenAiClient openAiClient;

    public SummarizationService(OpenAiClient openAiClient) {
        this.openAiClient = openAiClient;
    }

    public String summarizeArticle(String articleText) {
        if (articleText == null || articleText.isBlank()) {
            throw new IllegalArgumentException("Article text must not be empty");
        }
        return openAiClient.summarize(articleText);
    }
}
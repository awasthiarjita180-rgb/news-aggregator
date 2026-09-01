package com.newsaggregator.newsaggregator.client;

import com.newsaggregator.newsaggregator.summarization.OpenAiRequest;
import com.newsaggregator.newsaggregator.summarization.OpenAiResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Component
public class OpenAiClient {

    private final WebClient openAiWebClient;

    @Value("${openai.model}")
    private String model;

    public OpenAiClient(WebClient openAiWebClient) {
        this.openAiWebClient = openAiWebClient;
    }

    public String summarize(String articleText) {
        OpenAiRequest request = new OpenAiRequest(
                model,
                List.of(
                        new OpenAiRequest.Message("system",
                                "You are a helpful assistant that summarizes news articles concisely in 2-3 sentences."),
                        new OpenAiRequest.Message("user", articleText)
                )
        );

        OpenAiResponse response = openAiWebClient.post()
                .uri("/chat/completions")
                .bodyValue(request)
                .retrieve()
                .bodyToMono(OpenAiResponse.class)
                .block();

        if (response == null || response.choices().isEmpty()) {
            throw new RuntimeException("No response from OpenAI");
        }

        return response.choices().get(0).message().content();
    }
    public String analyzeSentiment(String text) {
        OpenAiRequest request = new OpenAiRequest(
                model,
                List.of(
                        new OpenAiRequest.Message("system",
                                "You are a sentiment analysis assistant. Classify the sentiment of the given text as exactly one word: Positive, Negative, or Neutral. Respond with only that single word, nothing else."),
                        new OpenAiRequest.Message("user", text)
                )
        );

        OpenAiResponse response = openAiWebClient.post()
                .uri("/chat/completions")
                .bodyValue(request)
                .retrieve()
                .bodyToMono(OpenAiResponse.class)
                .block();

        if (response == null || response.choices().isEmpty()) {
            throw new RuntimeException("No response from OpenAI");
        }

        return response.choices().get(0).message().content().trim();
    }
}
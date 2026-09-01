package com.newsaggregator.newsaggregator.translation;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Map;

@Service
public class TranslationService {

    private final WebClient webClient;

    @Value("${google.translate.api-key:}")
    private String apiKey;

    private static final String ENDPOINT = "https://translation.googleapis.com/language/translate/v2";

    public TranslationService() {
        this.webClient = WebClient.builder().baseUrl(ENDPOINT).build();
    }

    public TranslateResponse translate(String text, String targetLanguage) {
        Map<String, Object> body = Map.of(
                "q", text,
                "target", targetLanguage,
                "format", "text"
        );

        Map response = webClient.post()
                .uri(uriBuilder -> uriBuilder.queryParam("key", apiKey).build())
                .bodyValue(body)
                .retrieve()
                .bodyToMono(Map.class)
                .block();

        Map data = (Map) response.get("data");
        List<Map> translations = (List<Map>) data.get("translations");
        Map first = translations.get(0);

        String translatedText = (String) first.get("translatedText");
        String detectedSourceLanguage = (String) first.get("detectedSourceLanguage");

        return new TranslateResponse(translatedText, detectedSourceLanguage);
    }
}
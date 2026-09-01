package com.newsaggregator.newsaggregator.translation;

public class TranslateResponse {
    private String translatedText;
    private String detectedSourceLanguage;

    public TranslateResponse() {}

    public TranslateResponse(String translatedText, String detectedSourceLanguage) {
        this.translatedText = translatedText;
        this.detectedSourceLanguage = detectedSourceLanguage;
    }

    public String getTranslatedText() { return translatedText; }
    public void setTranslatedText(String translatedText) { this.translatedText = translatedText; }

    public String getDetectedSourceLanguage() { return detectedSourceLanguage; }
    public void setDetectedSourceLanguage(String detectedSourceLanguage) { this.detectedSourceLanguage = detectedSourceLanguage; }
}
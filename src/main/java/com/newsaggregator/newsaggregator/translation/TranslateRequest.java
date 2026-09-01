package com.newsaggregator.newsaggregator.translation;

public class TranslateRequest {
    private String text;
    private String targetLanguage; // e.g. "en", "hi", "fr"

    public TranslateRequest() {}

    public String getText() { return text; }
    public void setText(String text) { this.text = text; }

    public String getTargetLanguage() { return targetLanguage; }
    public void setTargetLanguage(String targetLanguage) { this.targetLanguage = targetLanguage; }
}
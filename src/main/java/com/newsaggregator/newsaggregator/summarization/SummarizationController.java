package com.newsaggregator.newsaggregator.summarization;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class SummarizationController {

    private final SummarizationService summarizationService;

    public SummarizationController(SummarizationService summarizationService) {
        this.summarizationService = summarizationService;
    }

    @PostMapping("/summarize")
    public SummarizeResponse summarize(@RequestBody SummarizeRequest request) {
        String summary = summarizationService.summarizeArticle(request.text());
        return new SummarizeResponse(summary);
    }

    public record SummarizeRequest(String text) {}
    public record SummarizeResponse(String summary) {}
}
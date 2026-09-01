package com.newsaggregator.newsaggregator.ocr;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api")
public class OcrController {

    private final OcrService ocrService;

    public OcrController(OcrService ocrService) {
        this.ocrService = ocrService;
    }

    @PostMapping("/ocr")
    public OcrResponse extractText(@RequestParam("image") MultipartFile image) {
        String text = ocrService.extractText(image);
        return new OcrResponse(text);
    }

    public record OcrResponse(String text) {}
}
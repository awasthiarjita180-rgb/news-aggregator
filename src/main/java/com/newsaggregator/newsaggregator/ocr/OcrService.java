package com.newsaggregator.newsaggregator.ocr;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

@Service
public class OcrService {

    private final Tesseract tesseract;

    public OcrService(Tesseract tesseract) {
        this.tesseract = tesseract;
    }

    public String extractText(MultipartFile imageFile) {
        try {
            BufferedImage image = ImageIO.read(imageFile.getInputStream());
            if (image == null) {
                throw new IllegalArgumentException("Uploaded file is not a valid image");
            }
            return tesseract.doOCR(image);
        } catch (IOException e) {
            throw new RuntimeException("Failed to read image file", e);
        } catch (TesseractException e) {
            throw new RuntimeException("OCR processing failed", e);
        }
    }
}
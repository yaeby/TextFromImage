package c8a.spring_ocr.controller;

import c8a.spring_ocr.service.OCRService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class Controller {

    @Autowired
    private OCRService ocrService;

    @PostMapping("/extract")
    public ResponseEntity<String> getText(@RequestParam MultipartFile file) {
        try {
            String text = ocrService.extractText(file);
            return ResponseEntity.ok(text);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

}

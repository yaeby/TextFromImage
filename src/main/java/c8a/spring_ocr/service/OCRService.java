package c8a.spring_ocr.service;

import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.lept;
import org.bytedeco.javacpp.tesseract;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

import static org.bytedeco.javacpp.lept.*;

@Service
public class OCRService {
    public String extractText(MultipartFile file) throws IOException {
        BytePointer outText;
        tesseract.TessBaseAPI api = new tesseract.TessBaseAPI();

        // Initialize Tesseract with the tessdata folder
        if (api.Init("src/main/resources/tessdata", "ENG") != 0) {
            throw new RuntimeException("Could not initialize tesseract.");
        }

        // Read the image directly from the file's InputStream
        InputStream inputStream = file.getInputStream();
        byte[] imageBytes = inputStream.readAllBytes();
        lept.PIX image = pixReadMem(imageBytes, imageBytes.length);
        if (image == null) {
            throw new RuntimeException("Failed to read image from input.");
        }

        // Perform OCR
        api.SetImage(image);
        outText = api.GetUTF8Text();
        String result = outText.getString();

        // Cleanup
        api.End();
        outText.deallocate();
        pixDestroy(image);

        return result;
    }
}

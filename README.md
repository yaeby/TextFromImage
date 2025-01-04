# Extract  text from image
A simple and small spring-boot example that extracts text from image using `Tesseract OCR`.

---

Basically this is the whole OCR:
```xml
<dependency>
    <groupId>org.bytedeco.javacpp-presets</groupId>
    <artifactId>tesseract</artifactId>
    <version>3.03-rc1-0.11</version>
</dependency>
```

and in resources u can find a `tessdata` folder with trained data.

---

<li> There are a lot of examples of apps in different languages that implement
`Tesseract OCR` to extract the text from image, but I did not find a clear example
how to use it without downloading SDK's or hard data on your pc.

<li> This example can be a good base for starting building a web app to extract 
text from images.
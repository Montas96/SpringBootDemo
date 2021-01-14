package com.umbrella.demoSpringBoot.Controller;

import com.umbrella.demoSpringBoot.Service.MediaFileService;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("api/v1/")
public class MediaFileController {

    private final MediaFileService mediaFileService;

    public MediaFileController(MediaFileService mediaFileService) {
        this.mediaFileService = mediaFileService;
    }

    @PostMapping("media-upload/{id}")
    public ResponseEntity<Void> uploadMedia(@RequestParam("file") MultipartFile file, @PathVariable String id) {
        mediaFileService.uploadMedia(file, id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("media-upload-encode/{id}")
    public ResponseEntity<Void> uploadEncodedMedia(@RequestBody String data, @PathVariable String id) {
        mediaFileService.uploadMediaFromBase64(data, id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("media-get-resource/{id}")
    public ResponseEntity<InputStreamResource> getMediaResource(@PathVariable String id) throws IOException {
        return mediaFileService.getMediaResource(id);
    }

    @GetMapping("media-get-url/{id}")
    public ResponseEntity<byte[]> getMediaURL(@PathVariable String id) {
        return mediaFileService.getMediaFromUrl(id);
    }

    @GetMapping("media-get-base64/{id}")
    public ResponseEntity<String> getMediaEncodedBase64(@PathVariable String id) {
        String data = mediaFileService.getMediaEncodedBase64(id);
        return ResponseEntity.ok().body(data);
    }
}


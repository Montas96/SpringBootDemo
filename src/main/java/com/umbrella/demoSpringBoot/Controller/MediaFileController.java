package com.umbrella.demoSpringBoot.Controller;

import com.umbrella.demoSpringBoot.Service.MediaFileService;
import com.umbrella.demoSpringBoot.Service.MediaService;
import com.umbrella.demoSpringBoot.Service.dto.MediaDTO;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("api/v1/")
public class MediaFileController {

    private final MediaFileService mediaFileService;
    private final MediaService mediaService;

    public MediaFileController(MediaFileService mediaFileService, MediaService mediaService) {
        this.mediaFileService = mediaFileService;
        this.mediaService = mediaService;
    }

    @PostMapping("media-upload/{id}")
    public ResponseEntity<MediaDTO> uploadMedia(@RequestParam("file") MultipartFile file,
                                                @PathVariable String id,
                                                @RequestParam(required = false) String name,
                                                @RequestParam(required = false) String mediaTypeId,
                                                @RequestParam(required = false) String relatedId) {
        MediaDTO mediaDTO = new MediaDTO();
        mediaDTO.setId(id);
        mediaDTO.setName(name);
        mediaDTO.setMediaTypeId(mediaTypeId);
        mediaDTO.setRelatedId(relatedId);
        mediaDTO = mediaFileService.uploadMedia(file, mediaDTO);
        return ResponseEntity.ok().body(mediaDTO);
    }

    @PostMapping("media-upload-encode/{id}")
    public ResponseEntity<Void> uploadEncodedMedia(@RequestBody String data, @PathVariable String id) {
        MediaDTO mediaDTO = new MediaDTO();
        mediaDTO.setId(id);
        mediaFileService.uploadMediaFromBase64(data, mediaDTO);
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

    @PostMapping("upload-user-media/{id}")
    public ResponseEntity<MediaDTO> uploadUserMedia(@RequestParam("file") MultipartFile file,
                                                    @PathVariable String id,
                                                    @RequestParam(required = false) String mediaTypeId) {
        MediaDTO mediaDTO = mediaService.createMediaFromUserId(id, mediaTypeId);
        mediaDTO = mediaFileService.uploadMedia(file, mediaDTO);
        return ResponseEntity.ok().body(mediaDTO);
    }
}


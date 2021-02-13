package com.umbrella.demoSpringBoot.Controller.MediaFile;

import com.umbrella.demoSpringBoot.Service.MediaFile.MediaFileService;
import com.umbrella.demoSpringBoot.Service.MediaService;
import com.umbrella.demoSpringBoot.Service.dto.MediaDTO;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
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

    /**
     * upload image and save media
     */
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

    /**
     * upload image from encoded base64 String and save media
     */
    @PostMapping("media-upload-encode/{id}")
    public ResponseEntity<Void> uploadEncodedMedia(@RequestBody String data, @PathVariable String id) {
        MediaDTO mediaDTO = new MediaDTO();
        mediaDTO.setId(id);
        mediaFileService.uploadMediaFromBase64(data, mediaDTO);
        return ResponseEntity.ok().build();
    }

    /**
     * retrieve file by file path
     *
     * @param mediaPath
     */
    @GetMapping("media-get-resource/{mediaPath}")
    public ResponseEntity<InputStreamResource> getMediaResource(@PathVariable String mediaPath) throws IOException {
        InputStreamResource image = mediaFileService.getMediaResource(mediaPath);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(MediaType.IMAGE_PNG_VALUE))
                .body(image);
    }

    /**
     * retrieve file by file path
     *
     * @param mediaPath
     */
    @GetMapping("media-get-url/{mediaPath}")
    public ResponseEntity<byte[]> getMediaURL(@PathVariable String mediaPath) {
        byte[] image = mediaFileService.getMediaFromUrl(mediaPath);
        return ResponseEntity.ok()
                .contentLength(image.length)
                .contentType(MediaType.parseMediaType(MediaType.IMAGE_PNG_VALUE))
                .body(image);
    }

    /**
     * retrieve file by file path and return encoded base64 image
     *
     * @param mediaPath
     */
    @GetMapping("media-get-base64/{id}")
    public ResponseEntity<String> getMediaEncodedBase64(@PathVariable String mediaPath) {
        String data = mediaFileService.getMediaEncodedBase64(mediaPath);
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


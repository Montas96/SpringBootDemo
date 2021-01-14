package com.umbrella.demoSpringBoot.Service;

import com.umbrella.demoSpringBoot.Domain.Media;
import com.umbrella.demoSpringBoot.Service.dto.MediaDTO;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface MediaFileService {

    void uploadMedia(MultipartFile file, Media media);
    MediaDTO uploadMedia(MultipartFile file, MediaDTO mediaDTO);

    void uploadMediaFromBase64(String data, Media media);
    MediaDTO uploadMediaFromBase64(String data, MediaDTO mediaDTO);

    ResponseEntity<InputStreamResource> getMediaResource(String id) throws IOException;

    ResponseEntity<byte[]> getMediaFromUrl(String id);

    String getMediaEncodedBase64(String id);

}

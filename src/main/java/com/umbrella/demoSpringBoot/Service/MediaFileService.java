package com.umbrella.demoSpringBoot.Service;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface MediaFileService {

    HttpEntity<byte[]> uploadMedia(MultipartFile file, String id);

    ResponseEntity<InputStreamResource> getMediaResource(String id) throws IOException;

    ResponseEntity<byte[]> getMediaFromUrl(String id);

    String getMediaEncodedBase64(String id);

}

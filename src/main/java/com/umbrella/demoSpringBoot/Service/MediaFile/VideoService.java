package com.umbrella.demoSpringBoot.Service.MediaFile;

import org.springframework.http.ResponseEntity;

public interface VideoService {

    ResponseEntity<byte[]> prepareContent(String fileName, String fileType, String range);
}

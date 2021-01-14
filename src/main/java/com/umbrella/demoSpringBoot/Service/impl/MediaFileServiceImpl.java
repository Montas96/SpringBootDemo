package com.umbrella.demoSpringBoot.Service.impl;

import com.umbrella.demoSpringBoot.Service.MediaFileService;
import com.umbrella.demoSpringBoot.Utils.FileUtils;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class MediaFileServiceImpl implements MediaFileService {

    public static final String DATA_IMAGE_PREFIX = "data:image/png;base64,";

    private final FileUtils fileUtils;

    public MediaFileServiceImpl(FileUtils fileUtils) {
        this.fileUtils = fileUtils;
    }

    @Override
    public HttpEntity<byte[]> uploadMedia(MultipartFile file, String id) {
        return fileUtils.saveFile(file, id);
    }

    @Override
    public String uploadMediaFromBase64(String data, String id) {
        return fileUtils.saveFileFromBase64(data, id);
    }

    @Override
    public ResponseEntity<InputStreamResource> getMediaResource(String id) throws IOException {
        InputStreamResource image = fileUtils.getFileUrl(id);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(MediaType.IMAGE_PNG_VALUE))
                .body(image);
    }

    @Override
    public ResponseEntity<byte[]> getMediaFromUrl(String id) {
        byte[] image = fileUtils.getFileURL(id);
        return ResponseEntity.ok()
                .contentLength(image.length)
                .contentType(MediaType.parseMediaType(MediaType.IMAGE_PNG_VALUE))
                .body(image);
    }

    @Override
    public String getMediaEncodedBase64(String id) {
        return DATA_IMAGE_PREFIX + fileUtils.getFileBase64Encoded(id);
    }
}

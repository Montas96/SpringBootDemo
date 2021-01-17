package com.umbrella.demoSpringBoot.Service;

import com.umbrella.demoSpringBoot.Domain.Media;
import com.umbrella.demoSpringBoot.Service.dto.MediaDTO;
import org.springframework.core.io.InputStreamResource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface MediaFileService {


    /**
     * Upload media file and save it with information from Media Entity.
     */
    void uploadMedia(MultipartFile file, Media media);

    /**
     * Upload media file and save it with information from MediaDTO
     * and Save media Entity with information about file.
     *
     * @param file     : file
     * @param mediaDTO : mediaDTO
     */
    MediaDTO uploadMedia(MultipartFile file, MediaDTO mediaDTO);

    /**
     * Upload media file and save it with information from Media Entity.
     *
     * @param data  : base64 data
     * @param media : media
     */
    void uploadMediaFromBase64(String data, Media media);

    /**
     * Upload media from encode base64 string and save it with information from MediaDTO
     * and Save media Entity with information about file.
     *
     * @param data     : base64 data
     * @param mediaDTO : mediaDTO
     */
    MediaDTO uploadMediaFromBase64(String data, MediaDTO mediaDTO);

    /**
     * Get media resource
     *
     * @param mediaPath
     */
    InputStreamResource getMediaResource(String mediaPath) throws IOException;

    /**
     * Get media resource as array of bytes
     *
     * @param mediaPath
     */
    byte[] getMediaFromUrl(String mediaPath);

    /**
     * Get media resource as encoded base64
     *
     * @param mediaPath
     */
    String getMediaEncodedBase64(String mediaPath);

}

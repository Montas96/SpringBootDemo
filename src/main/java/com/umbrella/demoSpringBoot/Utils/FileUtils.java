package com.umbrella.demoSpringBoot.Utils;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpEntity;
import org.springframework.web.multipart.MultipartFile;

/**
 *  use GridFS for storing files larger than 16 MB.
 */
public interface FileUtils {


    HttpEntity<byte[]> saveFile(MultipartFile file, String id);

    String saveFileFromBase64(String data, String id);

    InputStreamResource getFileUrl(String id);

    byte[] getFileURL(String id);

    String getFileBase64Encoded(String id);

}

package com.umbrella.demoSpringBoot.Utils;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.net.URI;

public interface FileUtils {

    /*
    *  use GridFS for storing files larger than 16 MB.
    * */
    HttpEntity<byte[]> saveFile(MultipartFile file, String id);

     InputStreamResource  getFileUrl(String id);

    byte[] getFileURL(String id);

    String getFileBase64Encoded(String id);
}

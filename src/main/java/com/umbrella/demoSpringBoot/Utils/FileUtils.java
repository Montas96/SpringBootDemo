package com.umbrella.demoSpringBoot.Utils;

import com.umbrella.demoSpringBoot.Domain.Media;
import org.springframework.core.io.InputStreamResource;
import org.springframework.web.multipart.MultipartFile;

/**
 * use GridFS for storing files larger than 16 MB.
 */
public interface FileUtils {


    void saveFile(MultipartFile file, Media media);

    void saveFileFromBase64(String data, Media media);

    InputStreamResource getFileUrl(String mediaPath);

    byte[] getFileURL(String mediaPath);

    String getFileBase64Encoded(String mediaPath);

}

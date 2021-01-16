package com.umbrella.demoSpringBoot.Utils.fileUtilsImp;

import com.umbrella.demoSpringBoot.Domain.Media;
import com.umbrella.demoSpringBoot.Utils.FileUtils;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class SaveFile implements FileUtils {

    private final static String PATH = "src/main/resources/images/";

    @Override
    public void saveFile(MultipartFile file, Media media) {

        try {
            String contentType = file.getContentType().replace("image/", "");
            saveImage(file.getBytes(), media.getId(), contentType);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveFileFromBase64(String data, Media media) {
        Pattern mime = Pattern.compile("^data:([a-zA-Z0-9]+/[a-zA-Z0-9]+).*,.*");
        String extension = "";
        String imageString = "";
        String base64ImageString = data;
        if (base64ImageString != null && base64ImageString.contains("data:image/")) {
            imageString = base64ImageString.split(",")[1];
            Matcher matcher = mime.matcher(base64ImageString);
            if (matcher.find()) {
                String delimiter = "[/]";
                extension = matcher.group(1).toLowerCase().split(delimiter)[1];
                try {
                    byte[] imageByte = Base64.getDecoder().decode(imageString);
                    saveImage(imageByte, media.getId(), extension);

                } catch (Exception e) {
                    throw new RuntimeException("Format exception");
                }
            }
        } else {
            throw new RuntimeException("Format exception");
        }
    }

    @Override
    public InputStreamResource getFileUrl(String id) {
        try {
            byte[] data = getImage(id, "png");
            if (data != null && data.length > 0) {
                return new InputStreamResource(new ByteArrayInputStream(data));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public byte[] getFileURL(String id) {
        try {
            byte[] data = getImage(id, "png");
            return data;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String getFileBase64Encoded(String id) {
        try {
            byte[] data = getImage(id, "png");
            return Base64.getEncoder().encodeToString(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void saveImage(byte[] image, String name, String extension) {
        try {
            ByteArrayInputStream mediaBytesInputStream = new ByteArrayInputStream(image);
            BufferedImage media = ImageIO.read(mediaBytesInputStream);
            mediaBytesInputStream.close();
            String mediaFilePath = getSingleMediaFilePath(name, extension);
            File mediaFile = new File(mediaFilePath);
            Path path1 = Paths.get(PATH);
            if (!path1.toFile().exists()) {
                Files.createDirectory(path1);
            }
            if (!mediaFile.exists()) {
                mediaFile.createNewFile();
            } else {
                Files.delete(mediaFile.toPath());
            }
            ImageIO.write(media, extension, mediaFile);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    private byte[] getImage(String name, String extension) throws IOException {
        File file = new File(getSingleMediaFilePath(name, extension));
        if (!file.exists()) {
            return null;
        }
        return org.apache.commons.io.FileUtils.readFileToByteArray(file);
    }

    private String getSingleMediaFilePath(String name, String mediaExtension) {
        return PATH + name + "." + mediaExtension;
    }
}

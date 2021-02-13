package com.umbrella.demoSpringBoot.Service.MediaFile;

import com.umbrella.demoSpringBoot.Domain.Media;
import com.umbrella.demoSpringBoot.Repository.MediaRepository;
import com.umbrella.demoSpringBoot.Service.dto.MediaDTO;
import com.umbrella.demoSpringBoot.Service.mapper.MediaMapper;
import com.umbrella.demoSpringBoot.Utils.FileUtils;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class MediaFileServiceImpl implements MediaFileService {

    public static final String DATA_IMAGE_PREFIX = "data:image/png;base64,";

    private final FileUtils fileUtils;
    private final MediaRepository mediaRepository;
    private final MediaMapper mediaMapper;

    public MediaFileServiceImpl(FileUtils fileUtils, MediaRepository mediaRepository, MediaMapper mediaMapper) {
        this.fileUtils = fileUtils;
        this.mediaRepository = mediaRepository;
        this.mediaMapper = mediaMapper;
    }

    @Override
    public void uploadMedia(MultipartFile file, Media media) {
        fileUtils.saveFile(file, media);
    }

    @Override
    public MediaDTO uploadMedia(MultipartFile file, MediaDTO mediaDTO) {
        Media media = mediaMapper.toEntity(mediaDTO);
        addInfoToMedia(media, file.getContentType(), file.getName());
        media = mediaRepository.save(media);
        fileUtils.saveFile(file, media);
        mediaDTO = mediaMapper.toDto(media);
        return mediaDTO;
    }

    private void addInfoToMedia(Media media, String contentType, String name) {
        if (!StringUtils.hasText(media.getName())) {
            media.setName(name);
        }
        media.setExtension(contentType);
        if (media.getMediaType() == null && StringUtils.startsWithIgnoreCase(contentType, "image/")) {
            media.setMediaType(new com.umbrella.demoSpringBoot.Domain.MediaType("MEDIA_OTHER_IMAGE"));
        }
        // String extension = contentType.split("/")[1];
        media.setPath("/api/v1/media-get-url/" + media.getName());
    }

    @Override
    public void uploadMediaFromBase64(String data, Media media) {
        fileUtils.saveFileFromBase64(data, media);
    }

    @Override
    public MediaDTO uploadMediaFromBase64(String data, MediaDTO mediaDTO) {
        Pattern mime = Pattern.compile("^data:([a-zA-Z0-9]+/[a-zA-Z0-9]+).*,.*");
        Matcher matcher = mime.matcher(data);
        String delimiter = "[/]";
        String extension = matcher.group(1).toLowerCase().split(delimiter)[1];
        Media media = mediaMapper.toEntity(mediaDTO);
        addInfoToMedia(media, extension, "");
        media = mediaRepository.save(media);
        fileUtils.saveFileFromBase64(data, media);
        mediaDTO.setId(media.getId());
        return mediaDTO;
    }

    @Override
    public InputStreamResource getMediaResource(String mediaPath) throws IOException {
        InputStreamResource image = fileUtils.getFileUrl(mediaPath);
        return image;
    }

    @Override
    public byte[] getMediaFromUrl(String id) {
        byte[] image = fileUtils.getFileURL(id);
        return image;
    }

    @Override
    public String getMediaEncodedBase64(String id) {
        return DATA_IMAGE_PREFIX + fileUtils.getFileBase64Encoded(id);
    }
}

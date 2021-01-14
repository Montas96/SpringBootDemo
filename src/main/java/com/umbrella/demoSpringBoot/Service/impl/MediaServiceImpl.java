package com.umbrella.demoSpringBoot.Service.impl;

import com.umbrella.demoSpringBoot.Repository.MediaRepository;
import com.umbrella.demoSpringBoot.Service.MediaService;
import com.umbrella.demoSpringBoot.Service.dto.MediaDTO;
import com.umbrella.demoSpringBoot.Service.mapper.MediaMapper;
import com.umbrella.demoSpringBoot.Utils.FileUtils;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class MediaServiceImpl implements MediaService {


    private final MediaRepository mediaRepository;
    private final MediaMapper mediaMapper;


    public MediaServiceImpl(MediaRepository mediaRepository, MediaMapper mediaMapper) {
        this.mediaRepository = mediaRepository;
        this.mediaMapper = mediaMapper;
    }


    @Override
    public MediaDTO create(MediaDTO mediaDTO) {
        return null;
    }

    @Override
    public MediaDTO update(MediaDTO mediaDTO) {
        return null;
    }

    @Override
    public void deleteOneById(String id) {

    }

    @Override
    public void deleteAllByIds(List<String> ids) {

    }

    @Override
    public void deleteAllByUserIdAndByTypeId(String user, String typeId) {

    }

    @Override
    public MediaDTO getOneById(String id) {
        return null;
    }

    @Override
    public List<MediaDTO> getAllMediaByUserId(String userId, Pageable pageable) {
        return null;
    }

    @Override
    public Page<MediaDTO> getAllMediaByTypeId(String typeId, Pageable pageable) {
        return null;
    }

    @Override
    public Page<MediaDTO> getAllMedia(Pageable pageable) {
        return null;
    }

}

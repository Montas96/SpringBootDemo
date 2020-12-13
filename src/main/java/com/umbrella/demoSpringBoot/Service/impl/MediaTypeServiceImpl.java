package com.umbrella.demoSpringBoot.Service.impl;

import com.umbrella.demoSpringBoot.Controller.Exception.MediaTypeExceptionNotFound;
import com.umbrella.demoSpringBoot.Domain.MediaType;
import com.umbrella.demoSpringBoot.Repository.MediaTypeRepository;
import com.umbrella.demoSpringBoot.Service.MediaTypeService;
import com.umbrella.demoSpringBoot.Service.dto.MediaTypeDTO;
import com.umbrella.demoSpringBoot.Service.mapper.MediaTypeMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class MediaTypeServiceImpl implements MediaTypeService {

    private final MediaTypeMapper mediaTypeMapper;
    private final MediaTypeRepository mediaTypeRepository;

    public MediaTypeServiceImpl(MediaTypeMapper mediaTypeMapper, MediaTypeRepository mediaTypeRepository) {
        this.mediaTypeMapper = mediaTypeMapper;
        this.mediaTypeRepository = mediaTypeRepository;
    }


    @Override
    public MediaTypeDTO create(MediaTypeDTO mediaTypeDTO) {
        MediaType mediaType = mediaTypeMapper.toEntity(mediaTypeDTO);
        mediaType = mediaTypeRepository.save(mediaType);
        mediaTypeDTO.setId(mediaType.getId());
        return mediaTypeDTO;
    }

    @Override
    public MediaTypeDTO update(MediaTypeDTO mediaTypeDTO) {
        MediaType mediaType = mediaTypeMapper.toEntity(mediaTypeDTO);
        mediaTypeRepository.save(mediaType);
        return mediaTypeDTO;
    }

    @Override
    public MediaTypeDTO getOneById(String id) {
        Optional<MediaType> optionalMediaType = mediaTypeRepository.findById(id);
        if (!optionalMediaType.isPresent()) {
            throw new MediaTypeExceptionNotFound();
        }
        return mediaTypeMapper.toDto(optionalMediaType.get());
    }

    @Override
    public List<MediaTypeDTO> getAll() {
        List<MediaType> mediaTypes = mediaTypeRepository.findAll();
        if (CollectionUtils.isEmpty(mediaTypes)) {
            return Collections.EMPTY_LIST;
        }
        return mediaTypeMapper.toDto(mediaTypes);
    }

    @Override
    public void deleteOneById(String id) {
        mediaTypeRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        mediaTypeRepository.deleteAll();
    }
}

package com.umbrella.demoSpringBoot.Service.impl;

import com.umbrella.demoSpringBoot.Domain.MediaType;
import com.umbrella.demoSpringBoot.Domain.User;
import com.umbrella.demoSpringBoot.Repository.MediaRepository;
import com.umbrella.demoSpringBoot.Repository.MediaTypeRepository;
import com.umbrella.demoSpringBoot.Repository.UserRepository;
import com.umbrella.demoSpringBoot.Service.MediaFile.MediaFileService;
import com.umbrella.demoSpringBoot.Service.MediaService;
import com.umbrella.demoSpringBoot.Service.dto.MediaDTO;
import com.umbrella.demoSpringBoot.Service.mapper.MediaMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

@Service
public class MediaServiceImpl implements MediaService {

    private static final String  MEDIA_OTHER_IMAGE = "6000262662bcd76bd95d7e6c";

    private final MediaRepository mediaRepository;
    private final MediaMapper mediaMapper;
    private final UserRepository userRepository;
    private final MediaTypeRepository mediaTypeRepository;
    private final MediaFileService mediaFileService;


    public MediaServiceImpl(MediaRepository mediaRepository, MediaMapper mediaMapper, UserRepository userRepository, MediaTypeRepository mediaTypeRepository, MediaFileService mediaFileService) {
        this.mediaRepository = mediaRepository;
        this.mediaMapper = mediaMapper;
        this.userRepository = userRepository;
        this.mediaTypeRepository = mediaTypeRepository;
        this.mediaFileService = mediaFileService;
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

    @Override
    public MediaDTO createMediaFromUserId(String userId, String mediaTypeId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        MediaDTO media = new MediaDTO();
        if(!optionalUser.isPresent()){
            throw new RuntimeException("Can not found user with id "+userId);
        }
        User user = optionalUser.get();
        setMediaType(media, mediaTypeId);
        media.setRelatedId(user.getId());
        media.setName(user.getUsername());
        return media;
    }

    private void setMediaType(MediaDTO media ,String mediaTypeId){
        if(StringUtils.hasText(mediaTypeId)){
            Optional<MediaType> optionalMediaType = mediaTypeRepository.findById(mediaTypeId);
            if(optionalMediaType.isPresent()){
                media.setMediaTypeId(optionalMediaType.get().getId());
                return;
            }
        }
        media.setMediaTypeId(MEDIA_OTHER_IMAGE);
    }
}

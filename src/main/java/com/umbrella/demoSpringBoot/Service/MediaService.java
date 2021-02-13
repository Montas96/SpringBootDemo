package com.umbrella.demoSpringBoot.Service;

import com.umbrella.demoSpringBoot.Service.dto.MediaDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MediaService {

    MediaDTO create(MediaDTO mediaDTO);

    MediaDTO update(MediaDTO mediaDTO);

    void deleteOneById(String id);

    void deleteAllByIds(List<String> ids);

    void deleteAllByUserIdAndByTypeId(String user, String typeId);

    MediaDTO getOneById(String id);

    List<MediaDTO> getAllMediaByUserId(String userId, Pageable pageable);

    Page<MediaDTO> getAllMediaByTypeId(String typeId, Pageable pageable);

    Page<MediaDTO> getAllMedia(Pageable pageable);

    MediaDTO createMediaFromUserId(String userId, String mediaTypeId);


}

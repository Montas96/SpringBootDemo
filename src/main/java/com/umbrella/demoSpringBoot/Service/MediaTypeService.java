package com.umbrella.demoSpringBoot.Service;

import com.umbrella.demoSpringBoot.Service.dto.MediaTypeDTO;

import java.util.List;
import java.util.Optional;

public interface MediaTypeService {

    MediaTypeDTO create(MediaTypeDTO mediaTypeDTO);

    MediaTypeDTO update(MediaTypeDTO mediaTypeDTO);

    MediaTypeDTO getOneById(String id);

    List<MediaTypeDTO> getAll();

    void deleteOneById(String ids);

    void deleteAll();
}

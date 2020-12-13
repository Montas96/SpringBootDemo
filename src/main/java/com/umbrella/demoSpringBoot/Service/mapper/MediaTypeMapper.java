package com.umbrella.demoSpringBoot.Service.mapper;

import com.umbrella.demoSpringBoot.Domain.MediaType;
import com.umbrella.demoSpringBoot.Service.dto.MediaTypeDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MediaTypeMapper extends GenericMapper<MediaType, MediaTypeDTO> {

    default MediaType fromId(String id) {
        if (id == null) {
            return null;
        }
        MediaType mediaType = new MediaType();
        mediaType.setId(id);
        return mediaType;
    }

}

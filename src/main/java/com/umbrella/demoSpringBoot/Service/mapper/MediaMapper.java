package com.umbrella.demoSpringBoot.Service.mapper;

import com.umbrella.demoSpringBoot.Domain.Media;
import com.umbrella.demoSpringBoot.Service.dto.MediaDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = MediaTypeMapper.class)
public interface MediaMapper extends GenericMapper<Media, MediaDTO> {

    @Mapping(source = "mediaTypeId", target = "mediaType")
    Media toEntity(MediaDTO dto);

    @Mapping(source = "mediaType.id", target = "mediaTypeId")
    MediaDTO toDto(Media entity);

    default Media fromId(String id) {
        if (id == null) {
            return null;
        }
        Media media = new Media();
        media.setId(id);
        return media;
    }
}

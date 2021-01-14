package com.umbrella.demoSpringBoot.Controller;

import com.umbrella.demoSpringBoot.Service.MediaTypeService;
import com.umbrella.demoSpringBoot.Service.dto.MediaTypeDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MediaTypeController {

    private final MediaTypeService mediaTypeService;

    public MediaTypeController(MediaTypeService mediaTypeService) {
        this.mediaTypeService = mediaTypeService;
    }

    @PostMapping("/mediaTypes")
    public ResponseEntity<MediaTypeDTO> create(@RequestBody  MediaTypeDTO mediaTypeDTO) {
        if (StringUtils.hasText(mediaTypeDTO.getId())) {
            throw new RuntimeException("mediaType can not have id for creation");
        }
        MediaTypeDTO response = mediaTypeService.create(mediaTypeDTO);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/mediaTypes")
    public ResponseEntity<MediaTypeDTO> update(@RequestBody MediaTypeDTO mediaTypeDTO) {
        if (!StringUtils.hasText(mediaTypeDTO.getId())) {
            throw new RuntimeException("mediaType must have an id for modification");
        }
        MediaTypeDTO response = mediaTypeService.update(mediaTypeDTO);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/mediaTypes/{id}")
    public ResponseEntity<MediaTypeDTO> findOneById(@PathVariable String id) {
        if (!StringUtils.hasText(id)) {
            throw new RuntimeException("Id must not be null or empty");
        }
        MediaTypeDTO response = mediaTypeService.getOneById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/mediaTypes")
    public ResponseEntity<List<MediaTypeDTO>> getAllMediaTypes() {
        List<MediaTypeDTO> response = mediaTypeService.getAll();
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/mediaTypes/{id}")
    public ResponseEntity<Void> deleteOne(@PathVariable String id) {
        mediaTypeService.deleteOneById(id);
        return ResponseEntity.ok().build();
    }

}

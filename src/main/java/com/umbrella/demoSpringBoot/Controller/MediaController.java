package com.umbrella.demoSpringBoot.Controller;

import com.umbrella.demoSpringBoot.Service.MediaService;
import com.umbrella.demoSpringBoot.Service.dto.MediaDTO;
import com.umbrella.demoSpringBoot.Utils.PaginationUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("api/")
public class MediaController {

    private final MediaService mediaService;

    public MediaController(MediaService mediaService) {
        this.mediaService = mediaService;
    }

    @PostMapping("medias")
    public ResponseEntity<MediaDTO> create(@RequestBody MediaDTO mediaDTO) {
        if (StringUtils.hasText(mediaDTO.getId())) {
            throw new RuntimeException("media already contains an id");
        }
        MediaDTO media = mediaService.create(mediaDTO);
        return ResponseEntity.ok().body(media);
    }

    @PutMapping("medias")
    public ResponseEntity<MediaDTO> update(@RequestBody MediaDTO mediaDTO) {
        if (!StringUtils.hasText(mediaDTO.getId())) {
            throw new RuntimeException("media doesn't contain an id");
        }
        MediaDTO media = mediaService.update(mediaDTO);
        return ResponseEntity.ok().body(media);
    }

    @GetMapping("medias/{id}")
    public ResponseEntity<MediaDTO> getById(@PathVariable String id) {
        if (StringUtils.hasText(id)) {
            throw new RuntimeException("id can not be null");
        }
        MediaDTO media = mediaService.getOneById(id);
        return ResponseEntity.ok().body(media);
    }

    @GetMapping("medias")
    public ResponseEntity<List<MediaDTO>> getAll(Pageable pageable, @RequestParam(required = false) String typeId, @RequestParam(required = false) String userId) {
        Page<MediaDTO> page;
        if (StringUtils.hasText(typeId)) {
            page = mediaService.getAllMediaByTypeId(typeId, pageable);
        } else if (StringUtils.hasText(userId)) {
            page = mediaService.getAllMediaByTypeId(userId, pageable);
        } else {
            page = mediaService.getAllMedia(pageable);
        }
        HttpHeaders headers = PaginationUtils.generatePaginationHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    @DeleteMapping("medias/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        if (!StringUtils.hasText(id)) {
            throw new RuntimeException("Id can not be null");
        }
        mediaService.deleteOneById(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("medias")
    public ResponseEntity<Void> delete(@RequestBody List<String> ids) {
        if (!CollectionUtils.isEmpty(ids)) {
            mediaService.deleteAllByIds(ids);
        }
        return ResponseEntity.ok().build();
    }


}

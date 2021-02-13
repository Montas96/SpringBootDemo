package com.umbrella.demoSpringBoot.Controller.MediaFile;

import com.umbrella.demoSpringBoot.Service.MediaFile.VideoService;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("api")
public class VideoStreamController {

    private final VideoService videoService;

    public VideoStreamController(VideoService videoService) {
        this.videoService = videoService;
    }

    @GetMapping("/stream/{fileType}/{fileName}")
    public Mono<ResponseEntity<byte[]>> streamVideo( @RequestHeader(value = "Range", required = false) String httpRangeList,
                                                    @PathVariable("fileType") String fileType,
                                                    @PathVariable("fileName") String fileName) {
        return Mono.just(videoService.prepareContent(fileName, fileType, httpRangeList));
    }
}

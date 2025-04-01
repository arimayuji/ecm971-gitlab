package arimayuji.eletiva.api.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import arimayuji.eletiva.api.dtos.CreateMusicRequest;
import arimayuji.eletiva.api.dtos.GetAllMusicResponse;
import arimayuji.eletiva.api.dtos.MusicResponse;
import arimayuji.eletiva.api.dtos.ReviewMusicRequest;
import arimayuji.eletiva.api.mappers.MusicDtoMapper;
import arimayuji.eletiva.application.useacases.CreateMusicUseCase;
import arimayuji.eletiva.application.useacases.GetAllMusicsUseCase;
import arimayuji.eletiva.application.useacases.ReviewMusicUseCase;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/music")
public class MusicController {

    private CreateMusicUseCase createMusicUseCase;

    private GetAllMusicsUseCase getAllMusicsUseCase;

    private ReviewMusicUseCase reviewMusicUseCase;

    public MusicController(CreateMusicUseCase createMusicUseCase, GetAllMusicsUseCase getAllMusicsUseCase,
            ReviewMusicUseCase reviewMusicUseCase) {
        this.createMusicUseCase = createMusicUseCase;

        this.getAllMusicsUseCase = getAllMusicsUseCase;

        this.reviewMusicUseCase = reviewMusicUseCase;
    }

    @PostMapping
    public ResponseEntity<MusicResponse> create(@RequestBody CreateMusicRequest body) {

        var result = createMusicUseCase.execute(body.musicName());

        var response = MusicDtoMapper.toResponse(result);

        return ResponseEntity.created(null).body(response);
    }

    @GetMapping
    public ResponseEntity<GetAllMusicResponse> getAll() {
        var result = getAllMusicsUseCase.execute();

        var response = MusicDtoMapper.toResponse(result);

        return ResponseEntity.ok().body(response);
    }

    @PatchMapping("/{musicName}/review")
    public ResponseEntity<Void> review(@RequestBody ReviewMusicRequest body, @PathVariable String musicName) {
        reviewMusicUseCase.execute(musicName, body.review());

        return ResponseEntity.noContent().build();
    }

}

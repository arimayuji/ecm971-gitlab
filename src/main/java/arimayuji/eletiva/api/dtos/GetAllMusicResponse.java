package arimayuji.eletiva.api.dtos;

import java.util.List;

public record GetAllMusicResponse(
        List<MusicResponse> musics) {
}
package arimayuji.eletiva.api.mappers;

import java.util.List;

import arimayuji.eletiva.api.dtos.CreateMusicRequest;
import arimayuji.eletiva.api.dtos.GetAllMusicResponse;
import arimayuji.eletiva.api.dtos.MusicResponse;
import arimayuji.eletiva.domain.entities.Music;

public class MusicDtoMapper {
    public static Music toDomain(CreateMusicRequest dto) {
        return new Music(
                dto.musicName());
    }

    public static GetAllMusicResponse toResponse(List<Music> musics) {
        List<MusicResponse> responseMusics = musics.stream().map((music) -> toResponse(music)).toList();
        return new GetAllMusicResponse(responseMusics);
    }

    public static MusicResponse toResponse(Music music) {
        return new MusicResponse(music.getId(), music.getName(), music.getReview());
    }
}
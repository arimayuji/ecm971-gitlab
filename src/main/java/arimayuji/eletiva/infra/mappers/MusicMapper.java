package arimayuji.eletiva.infra.mappers;

import java.util.List;

import arimayuji.eletiva.domain.entities.Music;
import arimayuji.eletiva.infra.entities.JpaMusic;

public class MusicMapper {

    private MusicMapper() {
    };

    public static Music toDomain(JpaMusic entity) {
        if (entity == null) {
            return null;
        }

        return new Music(
                entity.getId(),
                entity.getName(),
                entity.getReview());

    }

    public static JpaMusic fromDomain(Music music) {
        if (music == null) {
            return null;
        }

        return new JpaMusic(music.getName(), music.getReview());
    }

    public static List<Music> toDomainList(List<JpaMusic> entities) {
        return entities.stream()
                .map(MusicMapper::toDomain)
                .toList();
    }

    public static List<JpaMusic> fromDomainList(List<Music> musics) {
        return musics.stream()
                .map(MusicMapper::fromDomain)
                .toList();
    }
}

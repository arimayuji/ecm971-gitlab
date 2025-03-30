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
                entity.getName());

    }

    public static JpaMusic fromDomain(Music Music) {
        if (Music == null) {
            return null;
        }

        return new JpaMusic(Music.getName());
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

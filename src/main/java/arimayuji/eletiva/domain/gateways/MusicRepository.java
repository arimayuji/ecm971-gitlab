package arimayuji.eletiva.domain.gateways;

import java.util.List;
import java.util.Optional;

import arimayuji.eletiva.domain.entities.Music;

public interface MusicRepository {
    Music create(Music music);

    List<Music> getAll();

    Optional<Music> getByName(String musicName);

    void review(String musicName, int review);

}

package arimayuji.eletiva.infra.repositories.jpa;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import arimayuji.eletiva.domain.entities.Music;
import arimayuji.eletiva.domain.gateways.MusicRepository;
import arimayuji.eletiva.infra.entities.JpaMusic;
import arimayuji.eletiva.infra.mappers.MusicMapper;

public class JpaMusicRepository implements MusicRepository {

    private final SpringDataMusicRepository repository;

    public JpaMusicRepository(SpringDataMusicRepository repository) {
        this.repository = repository;
    }

    @Override
    public Music create(Music music) {
        JpaMusic entity = MusicMapper.fromDomain(music);

        var result = repository.save(entity);

        return MusicMapper.toDomain(result);
    }

    @Override
    public List<Music> getAll() {
        var result = repository.findAll();

        return result.stream()
                .map(MusicMapper::toDomain)
                .sorted(Comparator
                        .comparing(Music::getName)
                        .thenComparing(Comparator.comparing(Music::getReview).reversed()))
                .toList();
    }

    @Override
    public Optional<Music> getByName(String musicName) {
        Optional<JpaMusic> music = repository.findByName(musicName);

        if (music.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(MusicMapper.toDomain(music.get()));
    }

    @Override
    public void review(String musicName, int rating) {

        Optional<Music> result = getByName(musicName);

        if (result.isEmpty()) {
            throw new IllegalArgumentException("Música não encontrada");
        }

        if (rating < 0 || rating > 5) {
            throw new IllegalArgumentException("Nota inválida");
        }

        Music music = result.get();

        music.setReview(rating);

        repository.save(MusicMapper.fromDomain(music));
    }

}

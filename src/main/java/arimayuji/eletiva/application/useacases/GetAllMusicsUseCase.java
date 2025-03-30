package arimayuji.eletiva.application.useacases;

import java.util.List;

import arimayuji.eletiva.domain.entities.Music;
import arimayuji.eletiva.domain.gateways.MusicRepository;

public class GetAllMusicsUseCase {

    private MusicRepository musicRepository;

    public GetAllMusicsUseCase(MusicRepository musicRepository) {
        this.musicRepository = musicRepository;
    }

    public List<Music> execute() {
        return musicRepository.getAll();
    }
}

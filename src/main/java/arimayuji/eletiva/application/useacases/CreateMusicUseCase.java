package arimayuji.eletiva.application.useacases;

import arimayuji.eletiva.domain.entities.Music;
import arimayuji.eletiva.domain.gateways.MusicRepository;

public class CreateMusicUseCase {

    private MusicRepository musicRepository;

    public CreateMusicUseCase(MusicRepository musicRepository) {
        this.musicRepository = musicRepository;
    }

    public Music execute(String musicName) {

        Music music = new Music(musicName);
        
        return musicRepository.create(music);
    }
}

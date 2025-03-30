package arimayuji.eletiva.application.useacases;

import arimayuji.eletiva.domain.entities.Music;
import arimayuji.eletiva.domain.exceptions.MusicAlreadyExistsException;
import arimayuji.eletiva.domain.gateways.MusicRepository;

public class CreateMusicUseCase {

    private MusicRepository musicRepository;

    public CreateMusicUseCase(MusicRepository musicRepository) {
        this.musicRepository = musicRepository;
    }

    public Music execute(String musicName) {

        boolean musicAlreadyExists = musicRepository.getByName(musicName).isPresent();

        if (musicAlreadyExists) {
            throw new MusicAlreadyExistsException();
        }

        Music music = new Music(musicName);

        return musicRepository.create(music);
    }
}

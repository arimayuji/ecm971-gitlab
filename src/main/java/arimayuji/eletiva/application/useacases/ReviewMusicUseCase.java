package arimayuji.eletiva.application.useacases;

import arimayuji.eletiva.domain.gateways.MusicRepository;

public class ReviewMusicUseCase {

    private MusicRepository musicRepository;

    public ReviewMusicUseCase(MusicRepository musicRepository) {
        this.musicRepository = musicRepository;
    }

    public void execute(String musicName, int review) {
        musicRepository.review(musicName, review);
    }
}

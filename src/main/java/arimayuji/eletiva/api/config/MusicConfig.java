package arimayuji.eletiva.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import arimayuji.eletiva.api.mappers.MusicDtoMapper;
import arimayuji.eletiva.application.useacases.CreateMusicUseCase;
import arimayuji.eletiva.application.useacases.GetAllMusicsUseCase;
import arimayuji.eletiva.application.useacases.ReviewMusicUseCase;
import arimayuji.eletiva.domain.gateways.MusicRepository;
import arimayuji.eletiva.infra.repositories.jpa.JpaMusicRepository;
import arimayuji.eletiva.infra.repositories.jpa.SpringDataMusicRepository;

@Configuration
public class MusicConfig {

    @Bean
    public CreateMusicUseCase createMusicUseCase(MusicRepository musicRepository) {
        return new CreateMusicUseCase(musicRepository);
    }

    @Bean
    public GetAllMusicsUseCase getAllMusicsUseCase(MusicRepository musicRepository) {
        return new GetAllMusicsUseCase(musicRepository);
    }

    @Bean
    public ReviewMusicUseCase reviewMusicUseCase(MusicRepository musicRepository) {
        return new ReviewMusicUseCase(musicRepository);
    }

    @Bean
    public MusicRepository musicRepository(JpaMusicRepository jpaMusicRepository) {
        return jpaMusicRepository;
    }

    @Bean
    public JpaMusicRepository jpaMusicRepository(SpringDataMusicRepository springDataMusicRepository) {
        return new JpaMusicRepository(springDataMusicRepository);
    }

    @Bean
    public MusicDtoMapper musicDtoMapper() {
        return new MusicDtoMapper();
    }
}

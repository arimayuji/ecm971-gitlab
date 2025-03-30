package arimayuji.eletiva.api.dtos;

import java.util.UUID;

public record MusicResponse(
        UUID id,
        String musicName,
        int review) {
}

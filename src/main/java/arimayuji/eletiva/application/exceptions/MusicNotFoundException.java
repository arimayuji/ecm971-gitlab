package arimayuji.eletiva.application.exceptions;

import arimayuji.eletiva.domain.exceptions.BusinessException;

public class MusicNotFoundException extends BusinessException {
    public MusicNotFoundException() {
        super("Usuário não encontrado.");
    }
}

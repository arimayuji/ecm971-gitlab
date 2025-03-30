package arimayuji.eletiva.domain.exceptions;

public class MusicAlreadyExistsException extends BusinessException {
    public MusicAlreadyExistsException() {
        super("Usuário já existe.");
    }
}

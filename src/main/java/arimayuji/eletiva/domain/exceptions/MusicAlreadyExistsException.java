package arimayuji.eletiva.domain.exceptions;

public class MusicAlreadyExistsException extends BusinessException {
    public MusicAlreadyExistsException() {
        super("Música com esse nome já existe.");
    }
}

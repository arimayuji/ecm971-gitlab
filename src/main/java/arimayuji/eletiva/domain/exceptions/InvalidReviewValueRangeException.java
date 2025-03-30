package arimayuji.eletiva.domain.exceptions;

public class InvalidReviewValueRangeException extends BusinessException {
    public InvalidReviewValueRangeException() {
        super("Avaliações devem ser entre 1 a 5");
    }

}

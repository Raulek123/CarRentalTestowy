package pl.krzysztofwywial.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class RecordNotFoundException extends RuntimeException {

    @Serial
    private static final long SERIAL_VERSION_UID = 1L;

    public RecordNotFoundException(String message) {
        super(message);
    }

}

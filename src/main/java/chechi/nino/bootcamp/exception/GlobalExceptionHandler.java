package chechi.nino.bootcamp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> handlerUserNotFound (UserNotFoundException message) {

        return new ResponseEntity<>(message.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DuplicateEmailException.class)
    public ResponseEntity<String> handlerDuplicateEmail (DuplicateEmailException message) {

        return new ResponseEntity<>(message.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(RoomNotFoundException.class)
    public ResponseEntity<String> handlerRoomNotFound (RoomNotFoundException message) {

        return new ResponseEntity<>(message.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ReservationNotFoundException.class)
    public ResponseEntity<String> handlerReservationNotFound (ReservationNotFoundException message) {

        return new ResponseEntity<>(message.getMessage(), HttpStatus.NOT_FOUND);
    }
}

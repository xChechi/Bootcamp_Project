package chechi.nino.bootcamp.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

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

    @ExceptionHandler(TableNotFoundException.class)
    public ResponseEntity<String> handlerTableNotFound (TableNotFoundException message) {

        return new ResponseEntity<>(message.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(TableReservationNotFoundException.class)
    public ResponseEntity<String> handlerTableReservationNotFound (TableReservationNotFoundException message) {

        return new ResponseEntity<>(message.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CarNotFoundException.class)
    public ResponseEntity<String> handlerCarNotFound (CarNotFoundException message) {

        return new ResponseEntity<>(message.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EventNotFoundException.class)
    public ResponseEntity<String> handlerEventNotFound (EventNotFoundException message) {

        return new ResponseEntity<>(message.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BarSeatNotFoundException.class)
    public ResponseEntity<String> handlerBarSeatNotFound (BarSeatNotFoundException message) {

        return new ResponseEntity<>(message.getMessage(), HttpStatus.NOT_FOUND);
    }

}

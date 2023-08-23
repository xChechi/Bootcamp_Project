package chechi.nino.bootcamp.annotation;

import chechi.nino.bootcamp.repository.Reservation;
import jakarta.validation.Constraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.Payload;
import java.lang.annotation.*;
import java.util.List;

@Documented
@Constraint(validatedBy = {ValidReservationList.ReservationListValidator.class})
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidReservationList {

    String message() default "Invalid reservation list";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    class ReservationListValidator implements ConstraintValidator<ValidReservationList, List<? extends Reservation>> {

        @Override
        public boolean isValid(List<? extends Reservation> reservations, ConstraintValidatorContext context) {
            if (reservations == null) {
                return true;
            }

            for (Reservation reservation : reservations) {
                if (reservation == null || !isValidReservation(reservation)) {
                    return false;
                }
            }

            return true;
        }

        private boolean isValidReservation(Reservation reservation) {
            // Place custom validation logic here
            // Add interface for all required fields for that logic
            // Implement interface to your reservation entity
            // Add validation to your User Request DTO

            return reservation.getStartDate() != null &&
                    reservation.getEndDate() != null &&
                    reservation.getStartDate().isBefore(reservation.getEndDate());
        }
    }
}


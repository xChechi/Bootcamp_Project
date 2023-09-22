package chechi.nino.bootcamp.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.ReportAsSingleViolation;
import jakarta.validation.constraints.Pattern;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = {})
@Pattern.List({
        @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!#%*?&])[A-Za-z\\d@$!#%*?&]{8,30}$",
                message = "Should be between 8 and 30 chars with at least 1 lowercase, 1 uppercase, 1 special char, 1 digit and no whitespace"),
        @Pattern(regexp = "\\S+",
                message = "Password must not contain whitespaces")
})
@ReportAsSingleViolation
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface PasswordValidation2 {

    String message() default "Invalid password";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
/*
    It must be between 8 and 30 characters long
    It must have at least 1 lowercase character
    It must have at least 1 uppercase character
    It must have at least 1 special character
    It must have at least 1 digit character
    It must not contain whitespaces
 */

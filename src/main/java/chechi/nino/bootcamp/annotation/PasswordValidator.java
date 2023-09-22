package chechi.nino.bootcamp.annotation;

import jakarta.validation.*;
import jakarta.validation.constraints.Pattern;

import java.lang.annotation.*;


public class PasswordValidator implements ConstraintValidator<PasswordValidation, String> {

    @Override
    public void initialize(PasswordValidation constraintAnnotation) {
        // Initialization if needed
    }

    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {
        // Implement your custom password validation logic here
        // Return true if the password is valid, otherwise false
        return password != null && password.matches("^(?=.*[a-zA-Z])(?=.*\\d).{8,}$");
    }
}

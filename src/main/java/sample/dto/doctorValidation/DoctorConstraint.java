package sample.dto.doctorValidation;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = DoctorValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface DoctorConstraint {
    String message() default "This is not a Doctor!";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

package sample.dto.doctorValidation;

import sample.entity.User;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class DoctorValidator implements ConstraintValidator<DoctorConstraint, User>{

    @Override
    public void initialize(DoctorConstraint constraintAnnotation) {

    }

    @Override
    public boolean isValid(User value, ConstraintValidatorContext context) {
        return value.getRole() == "Doctor";
    }
}

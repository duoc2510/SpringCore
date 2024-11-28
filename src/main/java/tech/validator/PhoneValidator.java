package tech.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PhoneValidator implements ConstraintValidator<Phone, String> {

    @Override
    public void initialize(Phone paramA) {
    }

    @Override
    public boolean isValid(String phoneNo, ConstraintValidatorContext ctx) {
        if (phoneNo == null) {
            return false;
        }
        return phoneNo.matches("\\d{10}");
    }

}

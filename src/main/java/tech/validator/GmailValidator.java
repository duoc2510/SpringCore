package tech.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GmailValidator implements ConstraintValidator<Gmail, String> {

    private static final String GMAIL_REGEX = "^[A-Za-z0-9._%+-]+@gmail\\.com$"; // Regex kiểm tra Gmail

    @Override
    public void initialize(Gmail constraintAnnotation) {
    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        if (email == null) {
            return true; // Có thể kiểm tra thêm bằng @NotNull để đảm bảo không có email null
        }

        return email.matches(GMAIL_REGEX); // Trả về true nếu là email Gmail hợp lệ
    }

}

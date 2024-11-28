package tech.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class DateWithin30DaysValidator implements ConstraintValidator<ValidDate30Withins, LocalDate> {

    @Override
    public void initialize(ValidDate30Withins constraintAnnotation) {
        // Không cần thực hiện gì trong hàm này nếu không có yêu cầu đặc biệt
    }

    @Override
    public boolean isValid(LocalDate value, ConstraintValidatorContext context) {
        if (value == null) {
            return true; // Kiểm tra null đã được xử lý bởi @NotNull
        }

        // Lấy ngày hiện tại
        LocalDate currentDate = LocalDate.now();

        // Tính 30 ngày trước và 30 ngày sau so với ngày hiện tại
        LocalDate thirtyDaysBefore = currentDate.minus(30, ChronoUnit.DAYS);
        LocalDate thirtyDaysAfter = currentDate.plus(30, ChronoUnit.DAYS);
        LocalDate future = currentDate.plus(1, ChronoUnit.DAYS);

        // Kiểm tra ngày nhập vào có nằm trong phạm vi 30 ngày trước và sau không
        return !value.isBefore(future);
    }
}

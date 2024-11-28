package tech.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = DateWithin30DaysValidator.class) // Đặt validator
@Target({ ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidDate30Withins {
    String message() default "Chỉ nhận đặt lịch sau ngày hôm nay";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
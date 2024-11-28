/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tech.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.text.SimpleDateFormat;

public class DateFormatValidator implements ConstraintValidator<ValidDateFormat, String> {

    @Override
    public void initialize(ValidDateFormat constraintAnnotation) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.isEmpty()) {
            return true;  // Kiểm tra null được xử lý bởi @NotNull
        }

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setLenient(false);  // Không cho phép ngày không hợp lệ
        try {
            sdf.parse(value);  // Nếu không thể phân tích ngày, sẽ ném exception
            return true;
        } catch (Exception e) {
            return false;  // Nếu ngày không hợp lệ
        }
    }
}

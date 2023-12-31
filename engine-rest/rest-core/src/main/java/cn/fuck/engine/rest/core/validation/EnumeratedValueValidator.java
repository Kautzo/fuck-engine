package cn.fuck.engine.rest.core.validation;

import cn.fuck.engine.rest.core.annotation.EnumeratedValue;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * <p>Description: 枚举值校验逻辑 </p>
 */
public class EnumeratedValueValidator implements ConstraintValidator<EnumeratedValue, Object> {

    private String[] names;
    private int[] ordinals;

    @Override
    public void initialize(EnumeratedValue constraintAnnotation) {
        names = constraintAnnotation.names();
        ordinals = constraintAnnotation.ordinals();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext constraintValidatorContext) {
        if (value instanceof String) {
            for (String name : names) {
                if (name.equals(value)) {
                    return true;
                }
            }
        } else if (value instanceof Integer) {
            for (int ordinal : ordinals) {
                if (ordinal == (Integer) value) {
                    return true;
                }
            }
        }
        return false;
    }
}

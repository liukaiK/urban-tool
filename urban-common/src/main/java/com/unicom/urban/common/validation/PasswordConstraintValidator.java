package com.unicom.urban.common.validation;


import com.unicom.urban.common.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

/**
 * 校验密码规则
 *
 * @author liukai
 */
public class PasswordConstraintValidator implements ConstraintValidator<Password, String> {

    private final static Pattern PATTERN = Pattern.compile("^(?![A-Za-z0-9]+$)(?![a-z0-9\\W]+$)(?![A-Za-z\\W]+$)(?![A-Z0-9\\W]+$)[a-zA-Z0-9\\W]{8,16}$");

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (!StringUtils.hasText(value)) {
            return false;
        }
        return PATTERN.matcher(value).matches();
    }

}

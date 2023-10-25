package com.unicom.urban.common.validation;


import com.unicom.urban.common.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

/**
 * 校验手机号码
 *
 * @author liukai
 */
public class MobileNumberConstraintValidator implements ConstraintValidator<MobileNumber, String> {

    private final static Pattern PATTERN = Pattern.compile("^0?(13|14|15|18|19|16|17)[0-9]{9}$");

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (!StringUtils.hasText(value)) {
            return false;
        }
        return PATTERN.matcher(value).matches();
    }

}

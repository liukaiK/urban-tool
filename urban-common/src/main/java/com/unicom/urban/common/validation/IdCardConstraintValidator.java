package com.unicom.urban.common.validation;


import cn.hutool.core.util.IdcardUtil;
import com.unicom.urban.common.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 校验身份证规则
 *
 * @author liukai
 */
public class IdCardConstraintValidator implements ConstraintValidator<IdCard, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (!StringUtils.hasText(value)) {
            return false;
        }
        return IdcardUtil.isValidCard(value);
    }

}

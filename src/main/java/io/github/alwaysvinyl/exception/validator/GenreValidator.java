package io.github.alwaysvinyl.exception.validator;

import org.codehaus.plexus.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class GenreValidator implements ConstraintValidator<Genre, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return StringUtils.isEmpty(value) || io.github.alwaysvinyl.domain.model.Genre.of(value).isPresent();
    }
}
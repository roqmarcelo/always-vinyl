package io.github.alwaysvinyl.exception.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({PARAMETER})
@Retention(RUNTIME)
@Constraint(validatedBy = GenreValidator.class)
@Documented
public @interface Genre {

    String message() default "Please provide a valid genre.";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
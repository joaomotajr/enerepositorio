package br.com.eneeyes.validators;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * Created by asus on 29/09/14.
 */
@Documented
@Constraint(validatedBy = RegexValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface Regex {
    String pattern();

    String message() default "{Regex}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

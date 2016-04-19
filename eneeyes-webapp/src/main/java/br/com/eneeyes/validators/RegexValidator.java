package br.com.eneeyes.validators;

import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by asus on 29/09/14.
 */
public class RegexValidator implements ConstraintValidator<Regex, String> {
    String pattern;

    @Override
    public void initialize(Regex regex) {
        pattern = regex.pattern();
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return Pattern.matches(pattern, s);
    }
}

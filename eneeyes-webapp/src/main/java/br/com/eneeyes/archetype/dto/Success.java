package br.com.eneeyes.archetype.dto;

/**
 * Created by asus on 24/09/14.
 */
public class Success extends Result<Boolean> {
    public Success() {
        setValue(true);
    }

    public Success(String code, String message) {
        super(code, message);
        setValue(true);
    }

    @Override
    public String toString() {
        return "Success{" +
                "value=" + getValue() +
                ", code='" + getCode() + '\'' +
                ", message='" + getMessage() + '\'' +
                '}';
    }
}

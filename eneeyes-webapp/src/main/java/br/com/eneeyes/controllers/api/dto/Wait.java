package br.com.eneeyes.controllers.api.dto;

/**
 * Created by asus on 28/09/14.
 */
public class Wait extends Result<Boolean> {
    public Wait() {
    }

    public Wait(String code, String message) {
        super(code, message);
    }

    @Override
    public String toString() {
        return "Wait{" +
                "value=" + getValue() +
                ", code='" + getCode() + '\'' +
                ", message='" + getMessage() + '\'' +
                '}';
    }
}

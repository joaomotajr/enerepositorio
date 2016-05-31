package br.com.eneeyes.archetype.dto;

/**
 * Created by asus on 25/09/14.
 */
public abstract class Result<T> {
    private T value;

    private String code;

    private String message;

    public Result() {
    }

    protected Result(T value) {
        this.value = value;
    }

    protected Result(T value, String code) {
        this.value = value;
        this.code = code;
    }

    protected Result(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public Result(T value, String code, String message) {
        this.value = value;
        this.code = code;
        this.message = message;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Result{" +
                "value=" + value +
                ", code='" + code + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}

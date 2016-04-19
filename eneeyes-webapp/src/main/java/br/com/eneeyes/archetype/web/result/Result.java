package br.com.eneeyes.archetype.web.result;

/**
 * Created by Alan on 18/05/2014.
 */
public abstract class Result<T> extends ResultBase<T> {
    private T value;

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }
}

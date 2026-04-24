package util;


import exceptions.ValidationException;

@FunctionalInterface
public interface Validation <T>{
    public void validation(T value)throws ValidationException;
}

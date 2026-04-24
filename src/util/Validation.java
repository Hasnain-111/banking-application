package util;


import exceptions.ValidationException;

@FunctionalInterface
public interface Validation <String>{
    public void validation(String value)throws ValidationException;
}

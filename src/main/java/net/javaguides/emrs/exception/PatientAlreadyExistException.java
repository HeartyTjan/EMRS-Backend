package net.javaguides.emrs.exception;

public class PatientAlreadyExistException extends RuntimeException{
    public PatientAlreadyExistException(String message) {
        super(message);
    }
}

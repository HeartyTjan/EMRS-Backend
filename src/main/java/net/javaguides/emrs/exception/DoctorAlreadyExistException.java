package net.javaguides.emrs.exception;

public class DoctorAlreadyExistException extends RuntimeException {
    public DoctorAlreadyExistException(String message) {
        super(message);
    }
}

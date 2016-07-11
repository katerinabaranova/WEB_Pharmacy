package com.baranova.pharmacy.exception;

/**
 * Created by Ekaterina on 7/11/16.
 */
public class ExceptionDAO extends Exception{

    public ExceptionDAO() {
    }

    public ExceptionDAO(String message) {
        super(message);
    }

    public ExceptionDAO(String message, Throwable cause) {
        super(message, cause);
    }

    public ExceptionDAO(Throwable cause) {
        super(cause);
    }

}

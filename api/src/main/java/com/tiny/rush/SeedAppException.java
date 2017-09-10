package com.tiny.rush;

/********************************************************
 * File Name : ApiException.java
 * Author : ManhNV
 * Date : 2017-09-06
 * Description: 
 * Last-modified by : ManhNV
 * Last-modified : 2017-09-06
 ********************************************************/


public class SeedAppException extends RuntimeException {
    static final long serialVersionUID = 1;

    /**
     * Constructs a new ApiException.
     */
    public SeedAppException() {
        super();
    }

    /**
     * Constructs a new ApiException.
     *
     * @param message the detail message of this exception
     */
    public SeedAppException(String message) {
        super(message);
    }

    /**
     * Constructs a new ApiException.
     *
     * @param format the format string (see {@link java.util.Formatter#format})
     * @param args   the list of arguments passed to the formatter.
     */
    public SeedAppException(String format, Object... args) {
        this(String.format(format, args));
    }

    /**
     * Constructs a new ApiException.
     *
     * @param message   the detail message of this exception
     * @param throwable the cause of this exception
     */
    public SeedAppException(String message, Throwable throwable) {
        super(message, throwable);
    }

    /**
     * Constructs a new ApiException.
     *
     * @param throwable the cause of this exception
     */
    public SeedAppException(Throwable throwable) {
        super(throwable);
    }

    @Override
    public String toString() {
        // Throwable.toString() returns "ApiException:{message}". Returning just "{message}"
        // should be fine here.
        return getMessage();
    }
}

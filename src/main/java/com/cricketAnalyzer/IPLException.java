package com.cricketAnalyzer;

public class IPLException extends RuntimeException {
    ExceptionType type;
    public IPLException(String message, ExceptionType type) {
        super(message);
        this.type=type;
    }

    public  enum ExceptionType{
        NO_SUCH_FIELD,CSV_FILE_PROBLEM,NOT_A_VALID_TYPE, NO_SUCH_TYPE;


    }
}

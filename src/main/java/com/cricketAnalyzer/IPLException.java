package com.cricketAnalyzer;

public class IPLException extends RuntimeException {
    ExceptionType type;
    public IPLException(String message, ExceptionType type) {
        super(message);
        this.type=type;
    }

    public  enum ExceptionType{
       CSV_FILE_PROBLEM, NO_SUCH_TYPE;
    }
}

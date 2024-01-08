package com.ahievran.staj.exception.types;

public class EmailSendingException extends RuntimeException {
    public EmailSendingException(String meessage ){
        super(meessage);
    }
}

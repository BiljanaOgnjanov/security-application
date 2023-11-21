package com.example.saecurityapp.exception;

public class EmailExistException extends Exception{
    public EmailExistException() {
        super("Email address is already taken");
    }
}

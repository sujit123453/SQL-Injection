package com.blueThink.SQLi.customException;

public class IncorrectPassword extends RuntimeException{
    public IncorrectPassword(String message){
        super(message);
    }
}

package com.blueThink.SQLi.customException;

public class UserNotFound extends RuntimeException{
    public UserNotFound(String message){
        super(message);
    }
}

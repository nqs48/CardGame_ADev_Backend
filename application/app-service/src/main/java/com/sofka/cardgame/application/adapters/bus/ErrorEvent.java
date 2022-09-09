package com.sofka.cardgame.application.adapters.bus;

public class ErrorEvent {

    private final String classType;
    private final String message;

    public ErrorEvent(String classType, String message){
        super("cardgame.error");
        this.classType = classType;
        this.message = message;
    }

    public String getClassType() {
        return classType;
    }

    public String getMessage() {
        return message;
    }
}

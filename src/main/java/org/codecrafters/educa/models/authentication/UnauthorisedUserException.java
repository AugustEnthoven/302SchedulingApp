package org.codecrafters.educa.models.authentication;

public class UnauthorisedUserException extends Exception {
    public UnauthorisedUserException(String message){super(message);}
}

package com.rupesh.User_Service.ExceptionalHandling;

public class ResourceNotFound extends RuntimeException{

    public ResourceNotFound(String message) {
        super(message);
    }
}

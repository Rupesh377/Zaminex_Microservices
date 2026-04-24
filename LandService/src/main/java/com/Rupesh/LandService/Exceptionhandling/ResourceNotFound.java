package com.Rupesh.LandService.Exceptionhandling;

public class ResourceNotFound extends RuntimeException{

    public ResourceNotFound(String message) {
        super(message);
    }
}

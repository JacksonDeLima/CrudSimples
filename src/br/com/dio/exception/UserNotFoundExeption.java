package br.com.dio.exception;

public class UserNotFoundExeption extends RuntimeException{

    public UserNotFoundExeption(String message) {
        super(message);
    }
}

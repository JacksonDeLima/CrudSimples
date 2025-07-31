package br.com.dio.exception;

public class EmptyStorageExeption extends RuntimeException{

    public EmptyStorageExeption(String message) {
        super(message);
    }
}

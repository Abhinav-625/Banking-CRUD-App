package com.example.bankingDemo.exception;

public class NegativeInput extends RuntimeException {
    public NegativeInput(String message) {
        super(message);
    }
}

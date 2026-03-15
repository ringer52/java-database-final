package com.project.code.Exception;

// @RestControllerAdvice enables global exception handling across all REST controllers
@RestControllerAdvice
public class GlobalExceptionHandler {

    // handleJsonParseException - handles malformed or invalid JSON in request body
    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, Object> handleJsonParseException(HttpMessageNotReadableException e) {
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Invalid input: The data provided is not valid.");
        return response;
    }
}

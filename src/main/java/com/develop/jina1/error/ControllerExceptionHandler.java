package com.develop.jina1.error;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.apache.commons.lang3.StringUtils.defaultIfEmpty;
import static org.springframework.http.HttpStatus.*;

@Slf4j
@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(NOT_FOUND)
    protected ErrorResponseDto handleNotFoundException(NotFoundException ex) {
        log.error(ex.getMessage());
        return new ErrorResponseDto(defaultIfEmpty(ex.getMessage(), "Not found"));
    }

    @ExceptionHandler(UnauthorizedException.class)
    @ResponseStatus(UNAUTHORIZED)
    protected ErrorResponseDto handleNotUnauthorizedException(UnauthorizedException ex) {
        log.error(ex.getMessage());
        return new ErrorResponseDto(defaultIfEmpty(ex.getMessage(), "Access Denied"));
    }

    @ExceptionHandler(BadCredentialsException.class)
    @ResponseStatus(UNAUTHORIZED)
    protected ErrorResponseDto handleNotFoundException(BadCredentialsException ex) {
        log.error(ex.getMessage());
        return new ErrorResponseDto(defaultIfEmpty(ex.getMessage(), "Bad credentials!"));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(NOT_FOUND)
    protected ErrorResponseDto handleIllegalArgumentException(IllegalArgumentException ex) {
        log.error(ex.getMessage());
        return new ErrorResponseDto(ex.getMessage());
    }

    @ExceptionHandler(ConflictException.class)
    @ResponseStatus(CONFLICT)
    protected ErrorResponseDto handleConflictException(ConflictException ex) {
        log.error(ex.getMessage());
        return new ErrorResponseDto(defaultIfEmpty(ex.getMessage(), "Conflict"));
    }

    @ExceptionHandler(ForbiddenException.class)
    @ResponseStatus(FORBIDDEN)
    public ErrorResponseDto handleForbiddenException(ForbiddenException ex) {
        log.error(ex.getMessage());
        return new ErrorResponseDto(defaultIfEmpty(ex.getMessage(), "Forbidden"));
    }

    @ExceptionHandler(CalculationException.class)
    @ResponseStatus(INTERNAL_SERVER_ERROR)
    public ErrorResponseDto handleCalculationException(CalculationException ex) {
        log.error(ex.getMessage());
        return new ErrorResponseDto(defaultIfEmpty(ex.getMessage(), "Calculation failed"));
    }
}
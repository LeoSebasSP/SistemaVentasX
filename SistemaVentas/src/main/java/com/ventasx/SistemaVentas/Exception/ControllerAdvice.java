package com.ventasx.SistemaVentas.Exception;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RestControllerAdvice
public class ControllerAdvice{ // extends ResponseEntityExceptionHandler

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<ErrorMessageDto> AnyErrorExceptionHandler(Exception ex, WebRequest request) {
        Map<String, String> errors = new HashMap<>();
        errors.put("Generic Error", ex.getMessage());

        ErrorMessageDto errorMessageDto = ErrorMessageDto.builder().statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value()).timestamp(new Date())
                .errors(errors).description(request.getDescription(false)).build();
        return new ResponseEntity<ErrorMessageDto>(errorMessageDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorMessageDto> handleMethodArgumentNotValid(MethodArgumentNotValidException exception, WebRequest request) {
        Map<String, String> errors = new HashMap<>();
        for (ObjectError error : exception.getBindingResult().getAllErrors()) {
            String fieldName = ((FieldError) error).getField();
            String message   = error.getDefaultMessage();
            errors.put(fieldName, message);
        }

        ErrorMessageDto errorsFromBadRequestDto = ErrorMessageDto.builder()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .timestamp(new Date())
                .errors(errors)
                .description(request.getDescription(false))
                .build();

        return new ResponseEntity<>(errorsFromBadRequestDto, HttpStatus.BAD_REQUEST);
    }
}

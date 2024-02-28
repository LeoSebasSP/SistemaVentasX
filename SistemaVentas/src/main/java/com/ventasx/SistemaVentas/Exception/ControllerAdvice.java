package com.ventasx.SistemaVentas.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ControllerAdvice{ // extends ResponseEntityExceptionHandler

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<ErrorMapMessageDto> AnyErrorExceptionHandler(Exception ex, WebRequest request) {
        Map<String, String> errors = new HashMap<>();
        errors.put("Generic Error", ex.getMessage());

        ErrorMapMessageDto errorMapMessageDto = ErrorMapMessageDto.builder().statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value()).timestamp(new Date())
                .errors(errors).description(request.getDescription(false)).build();
        return new ResponseEntity<ErrorMapMessageDto>(errorMapMessageDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorMapMessageDto> handleMethodArgumentNotValid(MethodArgumentNotValidException exception, WebRequest request) {
        Map<String, String> errors = new HashMap<>();
        for (ObjectError error : exception.getBindingResult().getAllErrors()) {
            String fieldName = ((FieldError) error).getField();
            String message   = error.getDefaultMessage();
            errors.put(fieldName, message);
        }

        ErrorMapMessageDto errorsFromBadRequestDto = ErrorMapMessageDto.builder()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .timestamp(new Date())
                .errors(errors)
                .description(request.getDescription(false))
                .build();

        return new ResponseEntity<>(errorsFromBadRequestDto, HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(value = ResourceNotFound.class)
    public ResponseEntity<ErrorMessageDto> ResourceNotFound(ResourceNotFound ex, WebRequest request) {
        ErrorMessageDto errorMessageDto = ErrorMessageDto.builder().statusCode(ex.getStatus().value()).timestamp(new Date()).message(ex.getMessage())
                .description(request.getDescription(false)).build();
        return new ResponseEntity<>(errorMessageDto, ex.getStatus());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = DataErrorNullImport.class)
    public ResponseEntity<ErrorMessageDto> DataErrorNullImport(DataErrorNullImport ex, WebRequest request) {
        ErrorMessageDto errorMessageDto = ErrorMessageDto.builder().statusCode(ex.getStatus().value()).timestamp(new Date()).message(ex.getMessage())
                .description(request.getDescription(false)).build();
        return new ResponseEntity<>(errorMessageDto, ex.getStatus());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = ErrorGenericImport.class)
    public ResponseEntity<ErrorMapMessageDto> ErrorGenericImport(ErrorGenericImport ex, WebRequest request) {
        Map<String, String> errors = new HashMap<>();
        errors.put("Entity Error", ex.getResourceName());
        errors.put("Generic Error", ex.getErrorGeneric());
        errors.put("Row Error", String.valueOf(ex.getRowExcelError()));

        ErrorMapMessageDto errorMapMessageDto = ErrorMapMessageDto.builder().statusCode(HttpStatus.BAD_REQUEST.value()).timestamp(new Date())
                .errors(errors).description(request.getDescription(false)).build();
        return new ResponseEntity<ErrorMapMessageDto>(errorMapMessageDto, HttpStatus.BAD_REQUEST);
    }
}

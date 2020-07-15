package io.github.monthalcantara.clientes.controller;

import io.github.monthalcantara.clientes.exceptions.ApiError;
import io.github.monthalcantara.clientes.exceptions.RecursoNotFound;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;
import java.util.List;

@RestControllerAdvice
public class ApplicationControllerAdvice {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError handleValidationErros(MethodArgumentNotValidException ex){
        BindingResult bindingResult = ex.getBindingResult();
        List<String> messages = bindingResult.getAllErrors()
                .stream().map(objectError -> objectError.getDefaultMessage())
                .collect(Collectors.toList());

        return new ApiError(messages);

    }

    @ExceptionHandler(RecursoNotFound.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError handleNotFoundErros(RecursoNotFound ex){
               return new ApiError(ex.getMessage());

    }
}

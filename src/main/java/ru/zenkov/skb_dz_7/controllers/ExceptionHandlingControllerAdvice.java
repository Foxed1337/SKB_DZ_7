package ru.zenkov.skb_dz_7.controllers;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.zenkov.skb_dz_7.exeptions.NotFoundByIdException;
import ru.zenkov.skb_dz_7.exeptions.NotFoundByNameException;


import java.util.List;
import java.util.stream.Collectors;


@ControllerAdvice
public class ExceptionHandlingControllerAdvice {

    @ExceptionHandler(NotFoundByIdException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String onNotFoundByIdException(NotFoundByIdException e) {

        return String.format("Человек с id - %s - не найден", e.getPersonId());
    }

    @ExceptionHandler(NotFoundByNameException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String onNotFoundByNameException(NotFoundByNameException e) {

        return String.format("Человек с именем - %s - не найден", e.getPersonName());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ValidationErrorResponse onMethodArgumentNotValidException(
            MethodArgumentNotValidException e
    ) {
        List<Violation> violations = e.getBindingResult().getFieldErrors().stream()
                .map(error -> new Violation(error.getField(), error.getDefaultMessage()))
                .collect(Collectors.toList());
        return new ValidationErrorResponse(violations);
    }

}

@Getter
@RequiredArgsConstructor
class ValidationErrorResponse {

    private final List<Violation> violations;

}

@Getter
@RequiredArgsConstructor
class Violation {

    private final String fieldName;
    private final String message;

}

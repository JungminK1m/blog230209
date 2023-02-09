package shop.mtcoding.min.handler;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import shop.mtcoding.min.handler.exception.CustomException;
import shop.mtcoding.min.util.Script;

@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(CustomException.class)

    public String customException(CustomException e) {
        return Script.back(e.getMessage());
    }

}

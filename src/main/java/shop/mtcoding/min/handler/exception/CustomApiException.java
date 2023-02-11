package shop.mtcoding.min.handler.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public class CustomApiException extends RuntimeException {

    private HttpStatus status;

    public CustomApiException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    public CustomApiException(String message) {
        this(message, HttpStatus.BAD_REQUEST);
    }

}
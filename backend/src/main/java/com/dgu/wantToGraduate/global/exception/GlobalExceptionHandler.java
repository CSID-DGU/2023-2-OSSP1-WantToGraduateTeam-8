package com.dgu.wantToGraduate.global.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class GlobalExceptionHandler {

    //custom Exception
    @ExceptionHandler({CustomException.class})
    protected ResponseEntity<?> handlecustomException(CustomException e) {
        return ResponseEntity
            .status(e.getErrorCode().getHttpStatus())
            .body(new ErrorResponse(e));
    }

    //@valid Exception
    @ExceptionHandler({MethodArgumentNotValidException.class})
    protected ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        String message = e.getBindingResult().getFieldError().getDefaultMessage();
        CustomException validException = new CustomException(ErrorCode.INVALID_VALUE, message);

        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(new ErrorResponse(validException));
    }


    //일반 예외처리
//    @ExceptionHandler({Exception.class})
//    protected ResponseEntity<?> handleServerException(Exception ex) {
//        CustomException exception = new CustomException(SERVER_ERROR);
//        return ResponseEntity
//            .status(SERVER_ERROR.getHttpStatus())
//            .body(new ErrorResponse(exception));
//    }

}
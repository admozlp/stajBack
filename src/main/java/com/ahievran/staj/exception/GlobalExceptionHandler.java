package com.ahievran.staj.exception;

import org.springframework.security.access.AccessDeniedException;

import java.util.HashMap;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.ahievran.staj.core.result.ErrorResult;

import com.ahievran.staj.core.result.Result;
import com.ahievran.staj.exception.types.*;


@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public Result handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        ErrorResult errorResult = new ErrorResult();

        errorResult.setMessage("VALIDATİON EXCEPTION");
        errorResult.setCode(400);
        errorResult.setErrorFields(new HashMap<String, String>());

        for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
            errorResult.getErrorFields().put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        return errorResult;
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public Result handleMethodNotSupportedException(HttpRequestMethodNotSupportedException ex) {
        ErrorResult errorResult = new ErrorResult();

        errorResult.setMessage("Böyle bir endpoint mevcut değil");
        errorResult.setCode(500);
        return errorResult;
    }


    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result handleAllUncaughtException(Exception ex) {
        System.out.println("hata oldu");
        ErrorResult errorResult = new ErrorResult();

        errorResult.setMessage(ex.getMessage());
        errorResult.setCode(500);
        return errorResult;
    }

    @ExceptionHandler(EmailAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseEntity<ErrorResult> handleEmailAlreadyExistsException(EmailAlreadyExistsException ex) {
        ErrorResult errorResult = new ErrorResult();

        errorResult.setMessage(ex.getMessage());
        errorResult.setCode(400);
        return ResponseEntity.badRequest().body(errorResult);
    }

    @ExceptionHandler(BadCredentialsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Result> handleBadCredentialsException(BadCredentialsException ex) {
        System.out.println(ex.getLocalizedMessage());
        ErrorResult errorResult = new ErrorResult();

        errorResult.setMessage(ex.getMessage());
        errorResult.setCode(400);
        return ResponseEntity.badRequest().body(errorResult);
    }

    @ExceptionHandler(EmailDogrulamaException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Result> handleEmailDogrulamaException(EmailDogrulamaException ex) {
        ErrorResult errorResult = new ErrorResult();
        errorResult.setMessage(ex.getMessage());
        errorResult.setCode(400); // Buradaki kodu isteğinize göre ayarlayabilirsiniz.
        return ResponseEntity.badRequest().body(errorResult);
    }

    @ExceptionHandler(RolNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Result> handleRolNotFoundException(RolNotFoundException ex) {
        ErrorResult errorResult = new ErrorResult();
        errorResult.setMessage(ex.getMessage());
        errorResult.setCode(400); // Buradaki kodu isteğinize göre ayarlayabilirsiniz.
        return ResponseEntity.badRequest().body(errorResult);
    }


    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Result> handleAccessDeniedException(AccessDeniedException ex) {
        ErrorResult errorResult = new ErrorResult();
        errorResult.setMessage("Yetkiniz yok");
        errorResult.setCode(403);
        return ResponseEntity.badRequest().body(errorResult);
    }

    @ExceptionHandler(TokenNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Result> handleTokenNotFoundException(TokenNotFoundException ex) {
        ErrorResult errorResult = new ErrorResult();
        errorResult.setMessage(ex.getMessage());
        errorResult.setCode(403);
        return ResponseEntity.badRequest().body(errorResult);
    }

    @ExceptionHandler(EmailSendingException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Result> handleEmailSendingException(EmailSendingException ex) {
        ErrorResult errorResult = new ErrorResult();
        errorResult.setMessage(ex.getMessage());
        errorResult.setCode(403);
        return ResponseEntity.badRequest().body(errorResult);
    }

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Result> handleUserNotFoundException(UserNotFoundException ex) {
        ErrorResult errorResult = new ErrorResult();
        errorResult.setMessage(ex.getMessage());
        errorResult.setCode(403);
        return ResponseEntity.badRequest().body(errorResult);
    }

    @ExceptionHandler(OgrenciNoAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Result> handleOgrenciNoAlreadyExistsException(OgrenciNoAlreadyExistsException ex) {
        ErrorResult errorResult = new ErrorResult();
        errorResult.setMessage(ex.getMessage());
        errorResult.setCode(403);
        return ResponseEntity.badRequest().body(errorResult);
    }

    @ExceptionHandler(TcNoAlreadyException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Result> handleTcNoAlreadyException(TcNoAlreadyException ex) {
        ErrorResult errorResult = new ErrorResult();
        errorResult.setMessage(ex.getMessage());
        errorResult.setCode(403);
        return ResponseEntity.badRequest().body(errorResult);
    }

    @ExceptionHandler(CustomExpiredJwtException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Result> handleCustomExpiredJwtException(CustomExpiredJwtException ex) {
        ErrorResult errorResult = new ErrorResult();
        errorResult.setMessage(ex.getMessage());
        errorResult.setCode(403);
        return ResponseEntity.badRequest().body(errorResult);
    }
}

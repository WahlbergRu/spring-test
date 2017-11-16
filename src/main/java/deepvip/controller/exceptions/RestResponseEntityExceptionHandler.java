package deepvip.controller.exceptions;

import deepvip.util.GenericResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {


    @Autowired
    private MessageSource messages;

    // 400
    @ExceptionHandler({ ExceptionMessage.class })
    public ResponseEntity<Object> handleBindException(final Exception exception, final WebRequest request) {
        logger.error("400 Status Code", exception);
        final GenericResponse bodyOfResponse = new GenericResponse("handle Bind Exception", exception.getMessage());
        System.out.println(bodyOfResponse);
        return handleExceptionInternal(exception, bodyOfResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    //405
    @ExceptionHandler({ ExceptionMessage.class })
    public ResponseEntity<Object> handleMethodArgumentNotValid(final MethodArgumentNotValidException ex, final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
        logger.error("405 Status Code", ex);
        final BindingResult result = ex.getBindingResult();
        final GenericResponse bodyOfResponse = new GenericResponse(result.getAllErrors(), "Invalid" + result.getObjectName());
        return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.METHOD_NOT_ALLOWED, request);
    }

    //409
    @ExceptionHandler({ ExceptionMessage.class })
    public ResponseEntity<Object> handleAlreadyExist(final RuntimeException ex, final WebRequest request) {
        logger.error("409 Status Code", ex);
        final GenericResponse bodyOfResponse = new GenericResponse("AlreadyExist", "found in table");
        return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.CONFLICT, request);
    }
//    @ExceptionHandler({ ExceptionMessage.class })
//    public ResponseEntity<Object> handleInvalidOldPassword(final RuntimeException ex, final WebRequest request) {
//        logger.error("400 Status Code", ex);
//        final GenericResponse bodyOfResponse = new GenericResponse(messages.getMessage("message.invalidOldPassword", null, request.getLocale()), "InvalidOldPassword");
//        return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
//    }
//
//    @ExceptionHandler({ ExceptionMessage.class })
//    public ResponseEntity<Object> handleReCaptchaInvalid(final RuntimeException ex, final WebRequest request) {
//        logger.error("400 Status Code", ex);
//        final GenericResponse bodyOfResponse = new GenericResponse(messages.getMessage("message.invalidReCaptcha", null, request.getLocale()), "InvalidReCaptcha");
//        return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
//    }
//
//    // 404
//    @ExceptionHandler({ ExceptionMessage.class })
//    public ResponseEntity<Object> handleUserNotFound(final RuntimeException ex, final WebRequest request) {
//        logger.error("404 Status Code", ex);
//        final GenericResponse bodyOfResponse = new GenericResponse(messages.getMessage("message.userNotFound", null, request.getLocale()), "UserNotFound");
//        return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
//    }
//
//    // 409
//    @ExceptionHandler({ ExceptionMessage.class })
//
//    // 500
//    @ExceptionHandler({ ExceptionMessage.class })
//    public ResponseEntity<Object> handleMail(final RuntimeException ex, final WebRequest request) {
//        logger.error("500 Status Code", ex);
//        final GenericResponse bodyOfResponse = new GenericResponse(messages.getMessage("message.email.config.error", null, request.getLocale()), "MailError");
//        return new ResponseEntity<Object>(bodyOfResponse, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
//    }
//
//    @ExceptionHandler({ ExceptionMessage.class })
//    public ResponseEntity<Object> handleReCaptchaUnavailable(final RuntimeException ex, final WebRequest request) {
//        logger.error("500 Status Code", ex);
//        final GenericResponse bodyOfResponse = new GenericResponse(messages.getMessage("message.unavailableReCaptcha", null, request.getLocale()), "InvalidReCaptcha");
//        return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
//    }
//
//    @ExceptionHandler({ Exception.class })
//    public ResponseEntity<Object> handleInternal(final RuntimeException ex, final WebRequest request) {
//        logger.error("500 Status Code", ex);
//        final GenericResponse bodyOfResponse = new GenericResponse(messages.getMessage("message.error", null, request.getLocale()), "InternalError");
//        return new ResponseEntity<Object>(bodyOfResponse, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
//    }
}
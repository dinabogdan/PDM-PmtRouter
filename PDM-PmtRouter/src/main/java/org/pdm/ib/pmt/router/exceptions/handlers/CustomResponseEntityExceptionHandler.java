package org.pdm.ib.pmt.router.exceptions.handlers;

import lombok.extern.slf4j.Slf4j;
import org.pdm.ib.pmt.router.exceptions.CustAccountNotFoundException;
import org.pdm.ib.pmt.router.exceptions.CustomerNotFoundException;
import org.pdm.ib.pmt.router.exceptions.responses.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@Slf4j
@RestController
@ControllerAdvice
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    private final ResponseEntity handleAllExceptions(Exception exception, WebRequest request) {
        log.error("### Oups! We have an exception!!!");
        ExceptionResponse exceptionResponse = new ExceptionResponse(
                new Date(),
                exception.getMessage(),
                request.getDescription(false));

        return new ResponseEntity(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(CustomerNotFoundException.class)
    private final ResponseEntity handleCustomerNotFoundException
            (CustomerNotFoundException customerNotFoundException, WebRequest webRequest) {
        log.error("### Oups! We have a CustomerNotFoundException!!!");
        ExceptionResponse exceptionResponse = new ExceptionResponse(
                new Date(),
                customerNotFoundException.getMessage(),
                webRequest.getDescription(false));

        return new ResponseEntity(exceptionResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CustAccountNotFoundException.class)
    private final ResponseEntity handleAccountNotFoundException
            (CustAccountNotFoundException custAccountNotFoundException, WebRequest webRequest) {
        log.error("### Oups! We have a CustAccountNotFoundException!");
        ExceptionResponse exceptionResponse = new ExceptionResponse(
                new Date(),
                custAccountNotFoundException.getMessage(),
                webRequest.getDescription(false));

        return new ResponseEntity(exceptionResponse, HttpStatus.NOT_FOUND);
    }
}

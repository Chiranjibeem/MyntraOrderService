package com.myntra.order.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class OrderExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(OrderException.class)
	public final ResponseEntity<Object> handlerNotFound(OrderException ex) {
		ErrorResponse error = new ErrorResponse("404", ex.getMessage());
		return new ResponseEntity<Object>(error, HttpStatus.NOT_FOUND);
	}

	@Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String message = "Path," + ex.getRequestURL() + "\n"
                + "Message,The URL you have reached is not in service at this time (404)";
        logger.info("Path," + ex.getRequestURL() + "\n"
                + "Message,The URL you have reached is not in service at this time (404)");
        return new ResponseEntity<Object>(message, HttpStatus.NOT_FOUND);
    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String message = "Parameter :" + ex.getParameterName() + "\n"
                + "is missing in the url (404)";
        logger.info("Parameter :" + ex.getParameterName() + "\n"
                + "is missing in the url (404)");
        return new ResponseEntity<Object>(message, HttpStatus.NOT_FOUND);
    }
	
	
	
	
	
	

}

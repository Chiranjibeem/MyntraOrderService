package com.myntra.order.exception;

public class ErrorResponse 
{
    public ErrorResponse(String errorCode,String message) {
        super();
        this.message = message;
        this.errorCode = errorCode;
    }
 
    //General error message about nature of error
    private String message;
    private String errorCode;
 
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	
	
    
}
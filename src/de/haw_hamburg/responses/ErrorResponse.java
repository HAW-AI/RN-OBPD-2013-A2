package de.haw_hamburg.responses;

public class ErrorResponse extends AbstractRespone {
	
	private String message;
	
	private ErrorResponse(String message){
		this.message=message;
	}
	
	static ErrorResponse create(String message){
		return new ErrorResponse(message);
	}
	
	public String getMessage() {
		return message;
	}
	
	@Override
	public boolean isError() {
		return true;
	}
	
	@Override
	public String toString() {
		return Responses.ERROR+" "+message;
	}

}

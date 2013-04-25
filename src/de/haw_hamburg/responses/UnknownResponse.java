package de.haw_hamburg.responses;

public class UnknownResponse extends AbstractResponse {
	
	private UnknownResponse(){
		
	}

	static UnknownResponse create(){
		return new UnknownResponse();
	}
	
	@Override
	public boolean isUnknown() {
		return true;
	}
	
	@Override
	public String toString() {
		return Responses.UNKNOWN;
	}
	
}

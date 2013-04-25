package de.haw_hamburg.responses;

public class ByeResponse extends AbstractResponse {

	private ByeResponse(){
		
	}
	
	static ByeResponse create(){
		return new ByeResponse();
	}
	
	@Override
	public boolean isBye() {
		return true;
	}
	
	@Override
	public String toString() {
		return Responses.BYE;
	}
	
}

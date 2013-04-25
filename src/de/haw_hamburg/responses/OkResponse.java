package de.haw_hamburg.responses;

public class OkResponse extends AbstractRespone {
	
	private OkResponse(){
		
	}
	
	static OkResponse create(){
		return new OkResponse();
	}
	
	@Override
	public boolean isOk() {
		return true;
	}
	
	@Override
	public String toString() {
		return Responses.OK;
	}

}

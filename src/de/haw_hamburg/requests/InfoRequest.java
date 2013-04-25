package de.haw_hamburg.requests;

public class InfoRequest extends AbstractRequest {
	
	
	private InfoRequest() {
	}

	static InfoRequest create(){
		return new InfoRequest();
	}

	@Override
	public boolean isInfo() {
		return true;
	}
	
	@Override
	public String toString() {
		return Requests.INFO;
	}
	

}

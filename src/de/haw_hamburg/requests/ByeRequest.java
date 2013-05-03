package de.haw_hamburg.requests;

public class ByeRequest extends AbstractRequest {

	private ByeRequest() {
	}

	static ByeRequest create() {
		return new ByeRequest();
	}

	@Override
	public boolean isBye() {
		return true;
	}

	@Override
	public String toString() {
		return Requests.BYE;
	}

}

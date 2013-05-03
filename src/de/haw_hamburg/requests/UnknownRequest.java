package de.haw_hamburg.requests;

public class UnknownRequest extends AbstractRequest {

	private UnknownRequest() {
	}

	static UnknownRequest create() {
		return new UnknownRequest();
	}

	@Override
	public boolean isUnknown() {
		return true;
	}

	@Override
	public String toString() {
		return Requests.UNKNOWN;
	}

}

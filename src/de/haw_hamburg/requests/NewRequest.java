package de.haw_hamburg.requests;

public class NewRequest extends AbstractRequest {

	private String name;

	private NewRequest(String name) {
		this.name = name;
	}

	static NewRequest create(String name) {
		return new NewRequest(name);
	}

	public String getName() {
		return name;
	}

	@Override
	public boolean isNew() {
		return true;
	}

	@Override
	public String toString() {
		return Requests.NEW + " " + name;
	}

}

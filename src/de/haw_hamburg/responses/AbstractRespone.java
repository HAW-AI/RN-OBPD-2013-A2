package de.haw_hamburg.responses;

abstract class AbstractRespone implements Response {

	@Override
	public boolean isList() {
		return false;
	}

	@Override
	public boolean isBye() {
		return false;
	}

	@Override
	public boolean isError() {
		return false;
	}

	@Override
	public boolean isOk() {
		return false;
	}

	@Override
	public boolean isUnknown() {
		return false;
	}

}

package de.haw_hamburg.requests;

abstract class AbstractRequest implements Request {
 
	
	@Override
	public boolean isBye() {
		return false;
	}

	@Override
	public boolean isNew() {
		return false;
	}

	@Override
	public boolean isInfo() {
		return false;
	}
	
	@Override
	public boolean isUnknown() {
		return false;
	}
}

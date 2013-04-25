package de.haw_hamburg.responses;

public interface Response {
	
	boolean isList();
	
	boolean isBye();
	
	boolean isError();
	
	boolean isOk();
	
	boolean isUnknown();

}

package de.haw_hamburg.requests;

import java.util.logging.Logger;

public class Requests {
	
	static final String NEW="NEW";
	static final String INFO="INFO";
	static final String BYE="BYE";
	static final String UNKNOWN="UNKNOWN";
	
	private static Logger LOG=Logger.getLogger(Requests.class.getName());
	
	private Requests(){}
	
	public static Request newUser(String name){
		return NewRequest.create(name);
	}
	
	public static Request bye(){
		return ByeRequest.create();
	}
	
	public static Request info(){
		return InfoRequest.create();
	}

	public static Request fromRawRequest(String rawRequest){
		if(rawRequest==null){
			logUnknown(rawRequest);
			return UnknownRequest.create();
		}
		rawRequest=rawRequest.trim();
		if(rawRequest.startsWith(NEW)){
			return parseNew(rawRequest);
		}
		else if(rawRequest.equals(INFO)){
			return info();
		}
		else if(rawRequest.equals(BYE)){
			return bye();
		}
		else {
			logUnknown(rawRequest);
			return UnknownRequest.create();
		}
	}

	private static Request parseNew(String rawRequest) {
		if(rawRequest.length()< NEW.length()+2){
			logUnknown(rawRequest);
			return UnknownRequest.create();
		}
		else if(!rawRequest.substring(3,4).equals(" ")){
			logUnknown(rawRequest);
			return UnknownRequest.create();
		}
		else if(rawRequest.substring(4).trim().equals("")){
			logUnknown(rawRequest);
			return UnknownRequest.create();
		}
		else {
			return newUser(rawRequest.substring(4).trim()); 
		}
	}

	private static void logUnknown(String rawRequest){
		LOG.warning("Unknown request: \""+rawRequest+"\"");
	}
}

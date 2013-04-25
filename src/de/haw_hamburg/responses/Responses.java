package de.haw_hamburg.responses;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import de.haw_hamburg.common.User;

public class Responses {

	static final String OK = "OK";
	static final String ERROR = "ERROR";
	static final String UNKNOWN = "UNKNOWN";
	static final String BYE = "BYE";
	static final String LIST = "LIST";

	private static Logger LOG = Logger.getLogger(Responses.class.getName());

	private Responses() {

	}

	public static Response ok() {
		return OkResponse.create();
	}

	public static Response error(String message) {
		return ErrorResponse.create(message);
	}

	public static Response list(List<User> list) {
		return ListResponse.create(list);
	}

	public static Response bye() {
		return ByeResponse.create();
	}

	private static Response unknown() {
		return UnknownResponse.create();
	}

	public static Response fromRawResponse(String rawResponse) {
		if (rawResponse == null) {
			logUnknown(rawResponse);
			return unknown();
		}
		rawResponse = rawResponse.trim();
		if (rawResponse.equals(BYE)) {
			return bye();
		} else if (rawResponse.equals(OK)) {
			return ok();
		} else if (rawResponse.startsWith(ERROR)) {
			return parseError(rawResponse);
		} else if (rawResponse.startsWith(LIST)) {
			return parseList(rawResponse);
		} else {
			logUnknown(rawResponse);
			return unknown();
		}
	}

	private static Response parseError(String rawResponse) {
		String params = getParams(ERROR, rawResponse);
		if (params == null) {
			logUnknown(rawResponse);
			return unknown();
		} else {
			return error(params);
		}
	}

	private static Response parseList(String rawResponse) {
		String params = getParams(LIST, rawResponse);
		if (params == null) {
			logUnknown(rawResponse);
			return unknown();
		} else {
			try {
				String[] rawUserdata = params.split("\\s");
				int length = Integer.parseInt(rawUserdata[0]);
				if (length != (rawUserdata.length - 1) / 2) {
					LOG.warning("Illegal number of users. Expected " + length
							+ " was " + (rawUserdata.length - 1) / 2);
					logUnknown(rawResponse);
					return unknown();
				} else {
					List<User> users = new ArrayList<User>();
					for (int i = 1; i < rawUserdata.length; i += 2) {
						users.add(User.create(rawUserdata[i + 1],
								rawUserdata[i]));
					}
					return list(users);
				}
			} catch (NumberFormatException e) {
				LOG.warning("Could not get length of list response: "
						+ e.getMessage());
				logUnknown(rawResponse);
				return unknown();
			}
		}

	}

	private static String getParams(String keyword, String rawResponse){
		if(rawResponse.length()< keyword.length()+2){
			return null;
		}
		else if(!rawResponse.substring(keyword.length(),keyword.length()+1).equals(" ")){
			return null;
		}
		else if(rawResponse.substring(keyword.length()+1).trim().equals("")){
			return null;
		}
		else {
			return rawResponse.substring(keyword.length()+1).trim();
		}
	}

	private static void logUnknown(String rawResponse) {
		LOG.warning("Unknown response: \"" + rawResponse + "\"");
	}

}

package de.haw_hamburg.common;

public class User {

	private String name;
	private String hostname;
	
	public User(String name, String hostname) {
		this.name=name;
		this.hostname=hostname;
	}

	public static User create(String name, String hostname){
		return new User(name,hostname);
	}
	
	public String getHostname() {
		return hostname;
	}
	
	public String getName() {
		return name;
	}
	
}

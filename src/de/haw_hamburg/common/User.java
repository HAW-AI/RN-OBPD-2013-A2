package de.haw_hamburg.common;

public class User {

	private String name;
	private String hostname;

	public User(String name, String hostname) {
		this.name = name;
		this.hostname = hostname;
	}

	public static User create(String name, String hostname) {
		return new User(name, hostname);
	}

	public String getHostname() {
		return hostname;
	}

	public String getName() {
		return name;
	}

	public boolean equals(Object otherObject) {
		if (otherObject == null) {
			return false;
		} else if (otherObject instanceof User) {
			return ((User) otherObject).getName().equals(getName());
		} else {
			return false;
		}
	}
	
	@Override
	public String toString() {
		return getName() + "(" + getHostname() + ")";
	}

}

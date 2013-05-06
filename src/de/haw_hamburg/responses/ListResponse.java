package de.haw_hamburg.responses;

import java.net.InetAddress;;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import de.haw_hamburg.common.User;

public class ListResponse extends AbstractResponse {

	private List<User> list;

	private ListResponse(List<User> list) {
		this.list = list;
	}

	static ListResponse create(List<User> list) {
		return new ListResponse(list);
	}
	
	static ListResponse create(Map<String,InetAddress> map) {
		List<User> list=new ArrayList<User>();
		for(Map.Entry<String, InetAddress> entry:map.entrySet()){
			list.add(User.create(entry.getKey(), entry.getValue().toString()));
		}
		return new ListResponse(list);
	}

	@Override
	public boolean isList() {
		return true;
	}

	public List<User> getList() {
		return list;
	}

	@Override
	public String toString() {
		StringBuilder result = new StringBuilder(Responses.LIST);
		result.append(" ");
		result.append(list.size());
		for (User user : list) {
			result.append(" ");
			result.append(user.getHostname());
			result.append(" ");
			result.append(user.getName());
		}
		return result.toString();
	}

}

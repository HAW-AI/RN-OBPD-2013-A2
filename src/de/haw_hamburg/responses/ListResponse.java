package de.haw_hamburg.responses;

import java.util.List;

import de.haw_hamburg.common.User;

public class ListResponse extends AbstractResponse {
	
	private List<User> list;
	
	private ListResponse(List<User> list) {
		this.list=list;
	}

	static ListResponse create(List<User> list){
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
		StringBuilder result=new StringBuilder(Responses.LIST);
		result.append(" ");
		result.append(list.size());
		for(User user:list){
			result.append(" ");
			result.append(user.getHostname());
			result.append(" ");
			result.append(user.getName());
		}
		return result.toString();
	}
	
}

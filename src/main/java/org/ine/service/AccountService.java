package org.ine.service;

import org.ine.entities.AppRole;
import org.ine.entities.AppUser;

public interface AccountService {
	public AppUser saveUser(AppUser user);
	public AppRole saveRole(AppRole role);
	public void addRoleToUser(String username ,String roleName);
	public AppUser findUserByUserName(String Username);
	
	
}

package com.toptechsol.ipc.service;

import com.toptechsol.ipc.model.User;

public interface UserService {
	public User findUserByEmail(String email);
	public void saveUser(User user);
}

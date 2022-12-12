package com.dmitriyg.authMeetsCrud.service;

import com.dmitriyg.authMeetsCrud.model.User;

public interface UserService {

	void register(User user);

	User getCurrentAuthenticatedUser();

	User getById(int id);

}
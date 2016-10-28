package io.srqsoftware;

import java.util.List;

public interface UserService {
	long createUser(User x);
	void updateUser(User x);
	List<User> getAllUsers();
}

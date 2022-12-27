package ng.com.createsoftware.gymbuddybe.service;

import ng.com.createsoftware.gymbuddybe.model.User;

import java.util.List;

public interface UserService {

    User getUser(Long userId);

    User getUserById(Long userId);

    User editUser(User user, Long userId);

    User getUserByEmail(String email);

    List<User> getUsers();

    User deleteUser(Long userId);
}

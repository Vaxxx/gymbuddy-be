package ng.com.createsoftware.gymbuddybe.service;

import ng.com.createsoftware.gymbuddybe.model.User;
import ng.com.createsoftware.gymbuddybe.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    PasswordEncoder encoder;

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User getUser(Long userId) {
        return userRepository.findById(userId).orElseThrow(() ->
                new IllegalArgumentException("Could not find User iwth ID: " + userId));
    }

    @Override
    public User getUserById(Long userId) {
       User user = getUser(userId);
       return user;
    }

    @Override
    public User editUser(User user, Long userId) {
        User userToEdit = getUser(userId);
        userToEdit.setFirstname(user.getFirstname());
        userToEdit.setLastname(user.getLastname());
        userToEdit.setUsername(user.getUsername());
        userToEdit.setEmail(user.getEmail());
        userToEdit.setPhone(user.getPhone());
        userToEdit.setPassword( encoder.encode(user.getPassword()));
        userToEdit.setAge(user.getAge());
        userRepository.save(userToEdit);
        return userToEdit;
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() ->
                new IllegalArgumentException("Could not find User with email: " + email));
    }

    @Override
    public List<User> getUsers() {
        List<User> users = userRepository.findAll().stream().toList();
        return users;
    }

    @Override
    public User deleteUser(Long userId) {
        User user = getUser(userId);
        userRepository.delete(user);
        return user;
    }


}

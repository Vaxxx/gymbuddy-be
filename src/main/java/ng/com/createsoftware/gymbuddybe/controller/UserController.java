package ng.com.createsoftware.gymbuddybe.controller;


import lombok.AllArgsConstructor;
import ng.com.createsoftware.gymbuddybe.model.User;
import ng.com.createsoftware.gymbuddybe.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/v1/user")
@AllArgsConstructor
public class UserController {

    private UserService userService;

    //get all users
    @GetMapping("/users")
    public ResponseEntity<List<User>> getUsers(){
        List<User> users = userService.getUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    //get user by user Id
    @GetMapping("/get/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable("userId") final Long userId){
        User user = userService.getUserById(userId);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    //get user by user email
    @GetMapping("/getEmail/{email}")
    public ResponseEntity<User> getUserByEmail(@PathVariable("email") final String email){
        User user = userService.getUserByEmail(email);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    //edit user
    @PostMapping("/edit/{userId}")
    public ResponseEntity<User> editUser(@PathVariable("userId") final Long userId, @RequestBody User user){
        User userToEdit = userService.editUser(user, userId);
        return new ResponseEntity<>(userToEdit, HttpStatus.OK);
    }

    //delete user
    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable("userId") final Long userId){
        userService.deleteUser(userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

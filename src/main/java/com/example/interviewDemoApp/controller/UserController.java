package com.example.interviewDemoApp.controller;

import com.example.interviewDemoApp.dto.LoginDto;
import com.example.interviewDemoApp.model.User;
import com.example.interviewDemoApp.service.SequenceGeneratorService;
import com.example.interviewDemoApp.service.UserService;
//import com.example.interviewDemoApp.util.PasswordEncoder;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("demoapp/api/v1")
public class UserController {

    @Autowired
    UserService userService;

//    @Autowired
//    PasswordEncoder passwordEncoder;


    @Autowired
    SequenceGeneratorService sequenceGeneratorService;

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers(
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "10") Integer pageSize
    ) {
        List<User> users = userService.getAllUsers(pageNo,pageSize);
        return new ResponseEntity<List<User>>(users, HttpStatus.OK);
//        return userService.getAllUsers();
    }

    @PostMapping("/create-user")
    public void createUser(@Valid @RequestBody User user) {
        user.setId(sequenceGeneratorService.generateSequence(User.SEQUENCE_NAME));
//        String encryptedPassword =passwordEncoder.encode(user.getPassword());
//        user.setPassword(encryptedPassword);
        userService.saveUser(user);
    }

    @GetMapping("/search-username/{username}")
    public ResponseEntity<User> getUserByUsername(@PathVariable(value = "username") String username) {
        User user = userService.getUserByUserName(username);
        return ResponseEntity.ok().body(user);
    }

    @PutMapping("/update-user-details/{id}")
    public ResponseEntity<User> updateUserDetails(@PathVariable(value = "id") Long id, @Valid @RequestBody User userDetails){
        User user = userService.getUser(id);
        user.setAge(userDetails.getAge());
        user.setEmail(userDetails.getEmail());
        user.setUsername(userDetails.getUsername());
        user.setPassword(userDetails.getPassword());
        userService.saveUser(user);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/delete-user/{id}")
    public void deleteUser(@PathVariable(value = "id") Long id){
        User user = userService.getUser(id);
        userService.delete(user);
    }

    @GetMapping("/login")
    public ResponseEntity<String> login(@Valid @RequestBody LoginDto loginDetails) {
        return new ResponseEntity<>(userService.login(loginDetails), HttpStatus.OK);
    }


}

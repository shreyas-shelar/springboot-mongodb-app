package com.example.interviewDemoApp.service;

import com.example.interviewDemoApp.dto.LoginDto;
import com.example.interviewDemoApp.model.User;
import com.example.interviewDemoApp.repository.UserRepository;
//import com.example.interviewDemoApp.util.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

//    @Autowired
//    private PasswordEncoder passwordEncoder;

    public void saveUser(User user) {
        repository.save(user);
    }
    public User getUser(Long id) {
        return repository.findUserById(id).orElse(null);
    }

    public User getUserByUserName(String username) {
        return repository.findByUsername(username).orElse(null);
    }
    public List<User> getAllUsers(Integer pageNo, Integer pageSize){
        Pageable paging = PageRequest.of(pageNo, pageSize);
        Page<User> pagedResult = repository.findAll(paging);
        if(pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<User>();
        }
//        return repository.findAll();
    }
    public void deleteUser(String id) {
        repository.deleteById(id);
    }

    public void delete(User user) {
        repository.delete(user);
    }

    public String login(LoginDto loginDetails){
        User user = getUserByUserName(loginDetails.getUsername());
        if(!loginDetails.getPassword().equals(user.getPassword())){
            return "Password is incorrect";
        } else {
            return "Login Successful";
        }
    }
}

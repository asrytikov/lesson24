package com.example.lesson24.service;

import com.example.lesson24.model.User;
import com.example.lesson24.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User updateUser(User user) {
        Optional<User> userDb = this.userRepository.findById(user.getId());
        if (userDb.isPresent()){
            User userUpdate = userDb.get();
            userUpdate.setId(user.getId());
            userUpdate.setName(user.getName());
            userUpdate.setEmail(user.getEmail());
            userRepository.save(userUpdate);
            return userUpdate;
        }else{
            throw new EntityNotFoundException("User not found by id: " + user.getId());
        }
    }

    @Override
    public User getUserById(int id) {
        Optional<User> userDb = this.userRepository.findById(id);
        if (userDb.isPresent()){
            return userDb.get();
        }else{
            throw new EntityNotFoundException("User not found by id: " + id);
        }
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public void deleteUser(int id) {
        Optional<User> userDb = this.userRepository.findById(id);
        if (userDb.isPresent()){
            this.userRepository.delete(userDb.get());
        }else{
            throw new EntityNotFoundException("User not found by id: " + id);
        }
    }
}

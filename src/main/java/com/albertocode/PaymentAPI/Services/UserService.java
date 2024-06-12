package com.albertocode.PaymentAPI.Services;

import com.albertocode.PaymentAPI.DTOS.UserDTO;
import com.albertocode.PaymentAPI.Repositories.UserRepository;
import com.albertocode.PaymentAPI.domain.User.User;
import com.albertocode.PaymentAPI.domain.User.UserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;


    //method to check user and check if there's enough founds for the transaction
    public void validateTransaction(User sender, BigDecimal amount) throws Exception {

        if (sender.getUserType() == UserType.MERCHANT){
            throw new Exception("User of type lojist not allowed to process the transaction");
        }

        if (sender.getBalance().compareTo(amount) <0){

            throw new Exception("Insufficient Founds");
        }
    }

    //method to find user by id
    public User findUserById(Long id) throws Exception {
        return this.repository.findUserById(id).orElseThrow(() -> new Exception("User not Found"));

    }

    //method to create user
    public User createUser(UserDTO data){
        User newUser = new User(data);
        this.saveUser(newUser);
        return newUser;
    }

    public List<User> getAllUsers(){
         return this.repository.findAll();
    }

    //method to save the User in the database
    public void saveUser(User user){
        this.repository.save(user);
    }


}

package com.umaralikhon.service;

import com.umaralikhon.entity.User;
import com.umaralikhon.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository repository;

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public List<User> getAllUser(){
        List<User> allUser = repository.findAll();
        return allUser;
    }

    @Override
    public User saveUser(User user){
        String encodedPassword = this.passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        repository.save(user);

        return user;
    }

    @Override
    public User findByEmail(String email) throws Exception {
        Optional<User> optional = repository.findByEmail(email);

        if(!optional.isPresent()){
            throw new Exception("Email find exception!");
        }

        User user = optional.get();
        return user;
    }

    @Override
    public String getCode(String email) throws Exception {
        Optional<User> optional = repository.findByEmail(email);

        if(!optional.isPresent()){
            throw new Exception("Email find exception!");
        }

        User user = optional.get();
        String code = user.getStCode();
        return code;
    }

    @Override
    public User findByStCode(String code) throws Exception {
        Optional<User> optional = repository.findByStCode(code);

        if(!optional.isPresent()){
            throw new Exception("Code find exception!");
        }

        User user = optional.get();
        return user;
    }

    @Override
    public User findByBtCode(String code) throws Exception {
        Optional<User> optional = repository.findByBtCode(code);

        if(!optional.isPresent()){
            throw new Exception("Email find exception!");
        }

        User user = optional.get();
        return user;
    }

    @Override
    public User findByEmailAndStCode(String email, String code) throws Exception {
        Optional<User> optional = repository.findByEmailAndStCode(email, code);

//        if(!optional.isPresent()){
//            throw new Exception("Invalid data");
//        }

        User user = optional.get();
        return user;
    }
}

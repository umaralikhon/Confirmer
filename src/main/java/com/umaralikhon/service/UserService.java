package com.umaralikhon.service;

import com.umaralikhon.entity.User;

import java.util.List;

public interface UserService {
    List<User> getAllUser();
    User saveUser(User user);
    User findByEmail(String email) throws Exception;
    User findByStCode(String code) throws Exception;
    User findByBtCode(String code) throws Exception;
    String getCode(String email) throws Exception;
    User findByEmailAndStCode(String email, String code) throws Exception;
}

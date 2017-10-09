package com.queluz.spring.boot.dao;

import org.springframework.data.repository.CrudRepository;

import com.queluz.spring.boot.model.User;

public interface UserRepository extends CrudRepository<User, Long> {

//  Long countByEmail(String email);
}
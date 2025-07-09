package com.telusko.SpringSecEx.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.telusko.SpringSecEx.model.Users;

import java.util.List;


@Repository
public interface UserRepo extends JpaRepository<Users, Integer> {

    Users findByUsername(String username);
    List<Users> findByRoleIgnoreCase(String role);

}

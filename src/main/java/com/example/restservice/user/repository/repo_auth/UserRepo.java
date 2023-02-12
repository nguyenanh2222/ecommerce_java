package com.example.restservice.user.repository.repo_auth;

import com.example.restservice.dao.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository <User, Long> {
    User findByUserName(String userName);
}

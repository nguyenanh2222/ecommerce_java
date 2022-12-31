package com.example.restservice.user.repository.repo_auth;

import com.example.restservice.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository <UserEntity, Long> {
    UserEntity findByUsername(String username);
}

package com.example.restservice.user.repository.repo_auth;

import com.example.restservice.user.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepo extends JpaRepository <RoleEntity, Long> {
    RoleEntity findByName(String name);
}


package com.example.restservice.user.repository.repo_auth;


import com.example.restservice.dao.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepo extends JpaRepository<Role, Long> {
    Role findByName(String name);
}


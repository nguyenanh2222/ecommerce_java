package com.example.restservice.user.repository;
import com.example.restservice.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long>, PagingAndSortingRepository<UserEntity, Long> {
    UserEntity findByUserName(String userName);

    @Override
    UserEntity getReferenceById(Long id);
    UserEntity findByUsername(String username);
}
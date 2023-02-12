package com.example.restservice.user.repository;
import com.example.restservice.dao.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long>, PagingAndSortingRepository<User, Long> {
    User findByUserName(String userName);

    @Override
    User getReferenceById(Long id);

}
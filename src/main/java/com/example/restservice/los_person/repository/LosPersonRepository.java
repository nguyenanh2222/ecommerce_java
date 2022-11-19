package com.example.restservice.los_person.repository;
import com.example.restservice.los_person.entity.LosPersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;


import java.util.Optional;
@Repository
public interface LosPersonRepository extends JpaRepository<LosPersonEntity, Long>, PagingAndSortingRepository<LosPersonEntity, Long> {
    LosPersonEntity findByLosPersonEntity(String username);
    @Override
    LosPersonEntity getReferenceById(Long id);
}
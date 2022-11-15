package com.example.restservice.los_person.repository;
import com.example.restservice.los_person.entity.LosPersonEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.util.Optional;
@Repository
public interface LosPersonRepository extends JpaRepository<LosPersonEntity, Long>, PagingAndSortingRepository<LosPersonEntity, Long> {
    }
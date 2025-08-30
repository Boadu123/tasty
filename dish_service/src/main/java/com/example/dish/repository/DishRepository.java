package com.example.dish.repository;

import com.example.dish.models.Dish;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;


@Repository
public interface DishRepository extends JpaRepository<Dish,Long> {

    @EntityGraph(attributePaths = "menu")
    Page<Dish> findAll(Pageable pageable);

    @EntityGraph(attributePaths = "menu")
    Optional<Dish> findById(UUID id);
}

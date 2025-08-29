package com.example.dish.repository;

import com.example.dish.models.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuRepository extends JpaRepository<Menu,Long> {
    boolean existsByNameIgnoreCase(String name);
}

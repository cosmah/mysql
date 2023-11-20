package com.example.application.entity;

import jakarta.persistence.Entity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface EntityRepository extends JpaRepository<Entity, Long> {
}

package com.example.librarysystem.Repository;

import com.example.librarysystem.Entity.Patron;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PatronRepository extends JpaRepository<Patron, Long> {

    Optional<Patron> findPatronById(Long Id);
}

package com.example.librarysystem.Repository;
import com.example.librarysystem.Entity.BorrRec;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
public interface BorrRecRepository extends JpaRepository<BorrRec, Long> {
    //Optional<BorrRec> findBorrRecById(Long Id);
    Optional<BorrRec> findByBookId_AndPatronId(Long bookId, Long patronId);

}
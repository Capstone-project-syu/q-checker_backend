package syu.qchecker.university.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import syu.qchecker.university.domain.University;

import java.util.Optional;

@Repository
public interface UniversityRepository extends JpaRepository<University, Long> {
    
    Optional<University> findByDomain(String domain);
    
    Optional<University> findByName(String name);
    
    boolean existsByDomain(String domain);
}

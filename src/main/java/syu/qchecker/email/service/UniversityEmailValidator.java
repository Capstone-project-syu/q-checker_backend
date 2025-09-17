package syu.qchecker.email.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import syu.qchecker.university.domain.University;
import syu.qchecker.university.repository.UniversityRepository;

import java.util.Optional;

@Component
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UniversityEmailValidator {
    
    private final UniversityRepository universityRepository;

    public boolean isUniversityEmail(String email) {
        if (email == null || !email.contains("@")) {
            return false;
        }
        
        String domain = extractDomain(email);
        return universityRepository.existsByDomain(domain);
    }

    public Optional<University> getUniversityByEmail(String email) {
        if (email == null || !email.contains("@")) {
            return Optional.empty();
        }
        
        String domain = extractDomain(email);
        return universityRepository.findByDomain(domain);
    }

    private String extractDomain(String email) {
        return email.substring(email.indexOf("@") + 1).toLowerCase();
    }
}

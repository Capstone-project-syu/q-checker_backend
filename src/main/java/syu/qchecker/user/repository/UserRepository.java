package syu.qchecker.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import syu.qchecker.user.domain.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    Optional<User> findBySocialTypeAndSocialId(String socialType, String socialId);
}

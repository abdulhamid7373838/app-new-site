package uz.cosmos.appnewsite.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.cosmos.appnewsite.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByUsername(String username);

    Optional<User> findByUsername(String username);
}

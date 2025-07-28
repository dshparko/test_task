package by.dshparko.userservice.database.repository;

import by.dshparko.userservice.database.entity.Role;
import by.dshparko.userservice.database.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findUsersByRole(Role role);

    Optional<User> findByUsername(String username) throws UsernameNotFoundException;
}

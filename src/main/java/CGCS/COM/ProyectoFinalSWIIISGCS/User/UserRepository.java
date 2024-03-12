package CGCS.COM.ProyectoFinalSWIIISGCS.User;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @file: UserRepository
 * @author: EdwarMoya
 * @created: 11/03/2024
 * @HoraCreated: 10:34 p. m.
 */
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUsername (String username);
}

package mate.academy.bookstore.repository;

import java.util.Optional;

import mate.academy.bookstore.dto.UserRegistrationRequestDto;
import mate.academy.bookstore.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByEmail(String email);
}

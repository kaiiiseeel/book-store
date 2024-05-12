package mate.academy.bookstore.repository;

import java.util.Optional;
import mate.academy.bookstore.entity.Role;
import mate.academy.bookstore.entity.Role.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(RoleName name);
}

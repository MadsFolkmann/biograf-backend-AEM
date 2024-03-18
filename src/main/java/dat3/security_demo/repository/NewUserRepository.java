package dat3.security_demo.repository;

import dat3.security_demo.entity.newUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewUserRepository extends JpaRepository<newUser, String> {
}

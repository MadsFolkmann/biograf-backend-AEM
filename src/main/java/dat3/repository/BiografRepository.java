package dat3.repository;

import dat3.entity.Biograf;
import dat3.entity.Film;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BiografRepository extends JpaRepository<Biograf, Integer> {
    public Optional<Biograf> findByNavn(String navn);

}

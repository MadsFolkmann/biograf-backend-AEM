package dat3.repository;

import dat3.entity.Film;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FilmRepository extends JpaRepository<Film, Integer> {
    public Optional<Film> findByName(String name);
}

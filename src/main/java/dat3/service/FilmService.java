package dat3.service;

import dat3.entity.Film;
import dat3.repository.FilmRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class FilmService {

    FilmRepository filmRepository;

    public FilmService(FilmRepository filmRepository) {
        this.filmRepository = filmRepository;
    }

    public List<String> getAllFilms() {
        List<Film> films =  filmRepository.findAll();
        return films.stream().map((c)->new String(c.getTitel())).toList();
    }

}

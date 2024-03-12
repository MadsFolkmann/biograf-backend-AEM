package dat3.service;

import dat3.dto.FilmDto;
import dat3.entity.Film;
import dat3.repository.FilmRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service

public class FilmService {

    FilmRepository filmRepository;

    public FilmService(FilmRepository filmRepository) {
        this.filmRepository = filmRepository;
    }

    public List<FilmDto> getAllFilms() {
        List<Film> films = filmRepository.findAll();
        return films.stream()
                .map(film -> new FilmDto(film, true)) // Brug FilmDto-konstruktøren, der mapper alle filmoplysninger
                .toList();
    }

    public FilmDto getFilmById(int idInt) {
        Film film = filmRepository.findById(idInt).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Film ikke fundet"));
        return new FilmDto(film, false);
    }

    public FilmDto addFilm(FilmDto request) {
        Film newFilm = new Film();
        updateFilm(newFilm, request);
        filmRepository.save(newFilm);
        return new FilmDto(newFilm, false);
    }


    private void updateFilm(Film original, FilmDto r) {
        original.setTitel(r.getTitel());
        original.setVarighed(r.getVarighed());
        original.setGenre(r.getGenre());
        original.setBillede(r.getBillede());
        original.setEr3D(r.isEr3D());
        original.setFilmBeskrivelse(r.getFilmBeskrivelse());
        original.setFilmSprog(r.getFilmSprog());
    }


    public FilmDto editFilm(FilmDto request, int id) {
        Film filmToEdit = filmRepository.findById(id).orElseThrow(()
                -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Film ikke fundet"));

        // Opdater filmobjektet med oplysninger fra anmodningen
        updateFilm(filmToEdit, request);

        // Gem den opdaterede film i databasen
        filmRepository.save(filmToEdit);

        // Returner den opdaterede film som en DTO
        return new FilmDto(filmToEdit, false);
    }


    public ResponseEntity deleteFilm(int id) {
        Film film = filmRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Film ikke fundet"));
        filmRepository.delete(film);
        return new ResponseEntity(HttpStatus.OK);
    }
}

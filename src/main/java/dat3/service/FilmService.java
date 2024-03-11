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
        if (request.getId() != 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Du kan ikke skaffe id'et for en ny film");
        }
        Film film = filmRepository.findByTitel(request.getTitel()).
                orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Kun eksiterende film er tilladt"));
        Film newFilm = new Film();
        updateFilm(newFilm, request, film);
        filmRepository.save(newFilm);
        return new FilmDto(newFilm,false);
    }

    private void updateFilm(Film original, FilmDto r, Film film) {
        original.setTitel(r.getTitel());
        original.setVarighed(r.getVarighed());
        original.setGenre(r.getGenre());
        original.setBillede(r.getBillede());
        original.setEr3D(r.isEr3D());
        original.setFilmBeskrivelse(r.getFilmBeskrivelse());
        original.setFilmSprog(r.getFilmSprog());
    }


    public FilmDto editFilm(FilmDto request, int id) {
        if (request.getId() != id) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Du kan ikke ændre id for et eksiterende film id");
        }
        Film film = filmRepository.findByTitel(request.getTitel()).
                orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Kun eksisterende film er tilladt"));

        Film filmToEdit = filmRepository.findById(id).orElseThrow(()
                -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Film ikke fundet"));
        updateFilm(filmToEdit,request, film);
        filmRepository.save(filmToEdit);
        return new FilmDto(filmToEdit,false);
    }

    public ResponseEntity deleteFilm(int id) {
        Film film = filmRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Film ikke fundet"));
        filmRepository.delete(film);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}

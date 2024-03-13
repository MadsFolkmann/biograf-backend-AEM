package dat3.service;

import dat3.dto.FilmDtoRequest;
import dat3.dto.FilmDtoResponse;
import dat3.entity.Film;
import dat3.repository.FilmRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class FilmService {

    private final FilmRepository filmRepository;

    public FilmService(FilmRepository filmRepository) {
        this.filmRepository = filmRepository;
    }

    public List<FilmDtoResponse> getAllFilms() {
        List<Film> films = filmRepository.findAll();
        return films.stream()
                .map(FilmDtoResponse::new)
                .collect(Collectors.toList());
    }

    public FilmDtoResponse getFilmById(int id) {
        Film film = filmRepository.findById(id).orElseThrow(() ->
                new NoSuchElementException("Film not found"));
        return new FilmDtoResponse(film);
    }

    public FilmDtoResponse addFilm(FilmDtoRequest request) {
        Film newFilm = new Film();
        updateFilm(newFilm, request);
        filmRepository.save(newFilm);
        return new FilmDtoResponse(newFilm);
    }

    public FilmDtoResponse editFilm(FilmDtoRequest request, int id) {
        Film film = filmRepository.findById(id).orElseThrow(() ->
                new NoSuchElementException("Film not found"));
        updateFilm(film, request);
        filmRepository.save(film);
        return new FilmDtoResponse(film);
    }

    public ResponseEntity<Void> deleteFilm(int id) {
        Film film = filmRepository.findById(id).orElseThrow(() ->
                new NoSuchElementException("Film not found"));
        filmRepository.delete(film);
        return ResponseEntity.ok().build();
    }

    private void updateFilm(Film film, FilmDtoRequest request) {
        film.setTitel(request.getTitel());
        film.setVarighed(request.getVarighed());
        film.setGenre(request.getGenre());
        film.setBillede(request.getBillede());
        film.setEr3D(request.isEr3D());
        film.setFilmBeskrivelse(request.getFilmBeskrivelse());
        film.setFilmSprog(request.getFilmSprog());
    }
}

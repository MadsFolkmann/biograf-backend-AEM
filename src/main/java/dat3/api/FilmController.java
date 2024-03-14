package dat3.api;

import dat3.dto.FilmDtoRequest;
import dat3.dto.FilmDtoResponse;
import dat3.service.FilmService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/film")
public class FilmController {

    private final FilmService filmService;

    public FilmController(FilmService filmService) {
        this.filmService = filmService;
    }

    @GetMapping
    public List<FilmDtoResponse> getAllFilms() {
        return filmService.getAllFilms();
    }

    @GetMapping(path = "/{id}")
    public FilmDtoResponse getFilmById(@PathVariable int id) {
        return filmService.getFilmById(id);
    }

    @PostMapping
    public FilmDtoResponse addFilm(@RequestBody FilmDtoRequest request) {
        return filmService.addFilm(request);
    }

    @PutMapping(path = "/{id}")
    public FilmDtoResponse editFilm(@RequestBody FilmDtoRequest request, @PathVariable int id) {
        return filmService.editFilm(request, id);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteFilm(@PathVariable int id) {
        return filmService.deleteFilm(id);
    }
}

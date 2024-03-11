package dat3.api;

import dat3.dto.FilmDto;
import dat3.service.FilmService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/film")
public class FilmController {
    private FilmService filmService;

    public FilmController(FilmService filmService) {
        this.filmService = filmService;
    }
    @GetMapping
    public List<FilmDto> getAllFilms() {
        return filmService.getAllFilms();
    }

    @GetMapping(path ="/{id}")
    public FilmDto getRecipeById(@PathVariable int id) {
        return filmService.getFilmById(id);
    }

    @PostMapping
    public FilmDto addFilm(@RequestBody FilmDto request) {
        return filmService.addFilm(request);
    }
}

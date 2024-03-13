package dat3.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import dat3.entity.Film;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class FilmDtoResponse {
    private int id;
    private String titel;
    private int varighed;
    private String genre;
    private String billede;
    private boolean er3D;
    private String filmBeskrivelse;
    private String filmSprog;

    public FilmDtoResponse(Film film) {
        this.id = film.getId();
        this.titel = film.getTitel();
        this.varighed = film.getVarighed();
        this.genre = film.getGenre();
        this.billede = film.getBillede();
        this.er3D = film.isEr3D();
        this.filmBeskrivelse = film.getFilmBeskrivelse();
        this.filmSprog = film.getFilmSprog();

    }


}

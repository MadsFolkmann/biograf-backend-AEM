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
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FilmDto {
    private int id;
    private String titel;
    private int varighed;
    private String genre;
    private String billede;
    private boolean er3D;
    private String filmBeskrivelse;
    private String filmSprog;
    private LocalDateTime created;
    private LocalDateTime edited;

    public FilmDto(Film r) {
        this.id = r.getId();
        this.titel = r.getTitel();
        this.varighed = r.getVarighed();
        this.genre = r.getGenre();
        this.billede = r.getBillede();
        this.er3D = r.isEr3D();
        this.filmBeskrivelse = r.getFilmBeskrivelse();
        this.filmSprog = r.getFilmSprog();

    }

    public Film toEntity() {
        Film film = new Film();
        film.setId(this.id);
        film.setTitel(this.titel);
        film.setVarighed(this.varighed);
        film.setGenre(this.genre);
        film.setBillede(this.billede);
        film.setEr3D(this.er3D);
        film.setFilmBeskrivelse(this.filmBeskrivelse);
        film.setFilmSprog(this.filmSprog);
        return film;
    }

}

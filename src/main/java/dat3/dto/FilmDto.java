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
    private boolean er3D;
    private String filmBeskrivelse;
    private String filmSprog;
    private LocalDateTime created;
    private LocalDateTime edited;

    public FilmDto(Film r, boolean includeAll) {
        this.id = r.getId();
        this.titel = r.getTitel();
        this.varighed = r.getVarighed();
        this.genre = r.getGenre();
        this.er3D = r.isEr3D();
        this.filmBeskrivelse = r.getFilmBeskrivelse();
        this.filmSprog = r.getFilmSprog();

    }


}

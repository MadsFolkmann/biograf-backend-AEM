package dat3.security_demo.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    public FilmDto(int id, String titel, int varighed, String genre, boolean er3D, String filmBeskrivelse, String filmSprog) {
        this.id = id;
        this.titel = titel;
        this.varighed = varighed;
        this.genre = genre;
        this.er3D = er3D;
        this.filmBeskrivelse = filmBeskrivelse;
        this.filmSprog = filmSprog;

    }

}

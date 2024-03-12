package dat3.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "film")
public class Film {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true)
    private String titel;
    private int varighed;
    private String genre;
    private String billede;
    private boolean er3D;
    private String filmBeskrivelse;
    private String filmSprog;

    @CreationTimestamp
    private LocalDateTime created;

    @UpdateTimestamp
    private LocalDateTime edited;

    public Film(String titel, int varighed, String genre, String billede, boolean er3D, String filmBeskrivelse, String filmSprog) {
        this.titel = titel;
        this.varighed = varighed;
        this.genre = genre;
        this.billede = billede;
        this.er3D = er3D;
        this.filmBeskrivelse = filmBeskrivelse;
        this.filmSprog = filmSprog;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitel() {
        return titel;
    }

    public void setTitel(String titel) {
        this.titel = titel;
    }

    public int getVarighed() {
        return varighed;
    }

    public void setVarighed(int varighed) {
        this.varighed = varighed;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getBillede() {
        return billede;
    }

    public void setBillede(String billede) {
        this.billede = billede;
    }

    public boolean isEr3D() {
        return er3D;
    }

    public void setEr3D(boolean er3D) {
        this.er3D = er3D;
    }

    public String getFilmBeskrivelse() {
        return filmBeskrivelse;
    }

    public void setFilmBeskrivelse(String filmBeskrivelse) {
        this.filmBeskrivelse = filmBeskrivelse;
    }

    public String getFilmSprog() {
        return filmSprog;
    }

    public void setFilmSprog(String filmSprog) {
        this.filmSprog = filmSprog;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public LocalDateTime getEdited() {
        return edited;
    }

    public void setEdited(LocalDateTime edited) {
        this.edited = edited;
    }
}

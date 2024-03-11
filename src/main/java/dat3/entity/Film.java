package dat3.entity;

import jakarta.persistence.*;

@Table(name = "film")

@Entity
public class Film {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String titel;
    private int varighed;
    private String genre;
    private boolean er3D;
    private String filmBeskrivelse;
    private String filmSprog;

    public Film() {
        // Tom konstruktør nødvendig for JPA
    }

    public Film(String titel, int varighed, String genre, boolean er3D, String filmBeskrivelse, String filmSprog) {
        this.titel = titel;
        this.varighed = varighed;
        this.genre = genre;
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
}


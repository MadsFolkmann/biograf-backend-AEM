package dat3.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Forestilling {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    private Biograf biograf;
    @ManyToOne
    private Film film;
    @ManyToOne
    private Sal sal;
    @OneToMany
    private Set<Sæde> sæder;

    private LocalDateTime tidspunkt;

    public Forestilling() {
    }

    public Forestilling(Biograf biograf, Film film, Sal sal, LocalDateTime tidspunkt) {
        this.biograf = biograf;
        this.film = film;
        this.sal = sal;
        this.tidspunkt = tidspunkt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Biograf getBiograf() {
        return biograf;
    }

    public void setBiograf(Biograf biograf) {
        this.biograf = biograf;
    }

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    public Sal getSal() {
        return sal;
    }

    public void setSal(Sal sal) {
        this.sal = sal;
    }

    public LocalDateTime getTidspunkt() {
        return tidspunkt;
    }

    public void setTidspunkt(LocalDateTime tidspunkt) {
        this.tidspunkt = tidspunkt;
    }

    public Set<Sæde> getSæder() {
        return sæder;
    }

    public void setSæder(Set<Sæde> sæder) {
        this.sæder = sæder;
    }
}

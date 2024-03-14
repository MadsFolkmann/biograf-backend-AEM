package dat3.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Bestilling {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String navn;
    private String email;
    @ManyToMany
    private List<Forestilling> forestilling;
    @OneToMany
    private List<Sæde> sæder;
    private double pristotal;
    private LocalDateTime reservationstidspunkt;
    private boolean betalt;

    public Bestilling() {
    }

    public Bestilling(String navn, String email, List<Forestilling> forestilling, List<Sæde> sæder, double pristotal, LocalDateTime reservationstidspunkt, boolean betalt) {
        this.navn = navn;
        this.email = email;
        this.forestilling = forestilling;
        this.sæder = sæder;
        this.pristotal = pristotal;
        this.reservationstidspunkt = reservationstidspunkt;
        this.betalt = betalt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNavn() {
        return navn;
    }

    public void setNavn(String navn) {
        this.navn = navn;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Forestilling> getForestilling() {
        return forestilling;
    }

    public void setForestilling(List<Forestilling> forestilling) {
        this.forestilling = forestilling;
    }

    public List<Sæde> getSæder() {
        return sæder;
    }

    public void setSæder(List<Sæde> sæder) {
        this.sæder = sæder;
    }

    public double getPristotal() {
        return pristotal;
    }

    public void setPristotal(double pristotal) {
        this.pristotal = pristotal;
    }

    public LocalDateTime getReservationstidspunkt() {
        return reservationstidspunkt;
    }

    public void setReservationstidspunkt(LocalDateTime reservationstidspunkt) {
        this.reservationstidspunkt = reservationstidspunkt;
    }

    public boolean isBetalt() {
        return betalt;
    }

    public void setBetalt(boolean betalt) {
        this.betalt = betalt;
    }
}

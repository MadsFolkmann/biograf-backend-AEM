package dat3.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Ordre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String navn;
    private String email;
    @OneToMany
    private List<Forestilling> forestilling;
    @OneToMany
    private List<Sæde> sæder;
    private double pristotal;
    private LocalDateTime reservationstidspunkt;
    private boolean betalt;


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

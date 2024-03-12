package dat3.entity;

import dat3.enums.SædeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Sæde {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int række;
    private int sædeNummer;
    private SædeType sædeType;
    private double pris;
    private boolean optaget;

    public Sæde() {
    }

    public Sæde(int række, int sædeNummer, SædeType sædeType, double pris, boolean optaget) {
        this.række = række;
        this.sædeNummer = sædeNummer;
        this.sædeType = sædeType;
        this.pris = pris;
        this.optaget = optaget;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRække() {
        return række;
    }

    public void setRække(int række) {
        this.række = række;
    }

    public int getSædeNummer() {
        return sædeNummer;
    }

    public void setSædeNummer(int sædeNummer) {
        this.sædeNummer = sædeNummer;
    }

    public SædeType getSædeType() {
        return sædeType;
    }

    public void setSædeType(SædeType sædeType) {
        this.sædeType = sædeType;
    }

    public double getPris() {
        return pris;
    }

    public void setPris(double pris) {
        this.pris = pris;
    }

    public boolean isOptaget() {
        return optaget;
    }

    public void setOptaget(boolean optaget) {
        this.optaget = optaget;
    }
}

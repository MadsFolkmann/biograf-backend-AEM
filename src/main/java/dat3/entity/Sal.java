package dat3.entity;

import dat3.enums.SalType;
import jakarta.persistence.*;

import java.util.List;
import java.util.Set;

@Entity
public class Sal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int nummer;
    private int antalRækker;
    private int antalSæderPrRække;
    private SalType salType;


    public Sal() {
    }

    public Sal(int nummer, int antalRækker, int antalSæderPrRække, SalType salType) {
        this.nummer = nummer;
        this.antalRækker = antalRækker;
        this.antalSæderPrRække = antalSæderPrRække;
        this.salType = salType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNummer() {
        return nummer;
    }

    public void setNummer(int nummer) {
        this.nummer = nummer;
    }

    public int getAntalRækker() {
        return antalRækker;
    }

    public void setAntalRækker(int antalRækker) {
        this.antalRækker = antalRækker;
    }

    public int getAntalSæderPrRække() {
        return antalSæderPrRække;
    }

    public void setAntalSæderPrRække(int antalSæderPrRække) {
        this.antalSæderPrRække = antalSæderPrRække;
    }

    public SalType getSalType() {
        return salType;
    }

    public void setSalType(SalType salType) {
        this.salType = salType;
    }


}

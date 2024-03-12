package dat3.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Biograf {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String navn;
    private String adresse;
    private int antalSale;
    @OneToMany
    private List<Sal> sale;

    public Biograf() {
    }

    public Biograf(String navn, String adresse, int antalSale, List<Sal> sale) {
        this.navn = navn;
        this.adresse = adresse;
        this.antalSale = antalSale;
        this.sale = sale;
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

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public int getAntalSale() {
        return antalSale;
    }

    public void setAntalSale(int antalSale) {
        this.antalSale = antalSale;
    }

    public List<Sal> getSale() {
        return sale;
    }

    public void setSale(List<Sal> sale) {
        this.sale = sale;
    }
}

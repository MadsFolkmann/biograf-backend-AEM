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
}

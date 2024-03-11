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

}

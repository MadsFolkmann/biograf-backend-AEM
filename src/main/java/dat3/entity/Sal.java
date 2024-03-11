package dat3.entity;

import dat3.enums.SalType;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Sal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int nummer;
    private int antalRækker;
    private int antalSæderPrRække;
    private SalType salType;
    @OneToMany
    private List<Sæde> sæder;
}

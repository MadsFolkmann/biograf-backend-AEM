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
}

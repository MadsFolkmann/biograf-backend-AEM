package dat3.entity;

import dat3.enums.SædeType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "sæde")
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

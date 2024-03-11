package dat3.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Forestilling {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    private Biograf biograf;
    @ManyToOne
    private Film film;
    @ManyToOne
    private Sal sal;

    private LocalDateTime tidspunkt;
}

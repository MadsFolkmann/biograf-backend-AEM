package dat3.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "forestilling")
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
    @OneToMany
    private Set<Sæde> sæder;

    private LocalDateTime tidspunkt;
}

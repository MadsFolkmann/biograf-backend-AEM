package dat3.dto;

import dat3.entity.Forestilling;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class ForestillingDtoRequestUdenSæder {
    private int id;
    private BiografDtoRequest biograf;
    private FilmDtoRequest film;
    private SalDtoRequest sal;
    private LocalDateTime tidspunkt;

    public ForestillingDtoRequestUdenSæder(Forestilling forestilling){
        this.id = forestilling.getId();
        this.biograf = new BiografDtoRequest(forestilling.getBiograf());
        this.film = new FilmDtoRequest(forestilling.getFilm());
        this.sal = new SalDtoRequest(forestilling.getSal());
        this.tidspunkt = forestilling.getTidspunkt();
    }
}
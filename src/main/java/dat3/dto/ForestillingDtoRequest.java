package dat3.dto;


import dat3.entity.Forestilling;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ForestillingDtoRequest {
    private int id;
    private BiografDto biograf;
    private FilmDto film;
    private SalDto sal;
    private Set<SædeDto> sæder;
    private LocalDateTime tidspunkt;

    public ForestillingDtoRequest(Forestilling forestilling) {
        this.id = forestilling.getId();
        this.biograf = new BiografDto(forestilling.getBiograf());
        this.film = new FilmDto(forestilling.getFilm());
        this.sal = new SalDto(forestilling.getSal());
        this.sæder = forestilling.getSæder().stream().map(SædeDto::new).collect(Collectors.toSet());
        this.tidspunkt = forestilling.getTidspunkt();
    }
}

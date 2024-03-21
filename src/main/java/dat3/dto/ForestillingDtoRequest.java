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

    private BiografDtoRequest biograf;
    private FilmDtoRequest film;
    private SalDtoRequest sal;
    private Set<SædeDtoRequest> sæder;
    private LocalDateTime tidspunkt;

    public ForestillingDtoRequest(Forestilling forestilling) {
        this.id = forestilling.getId();
        this.biograf = new BiografDtoRequest(forestilling.getBiograf());
        this.film = new FilmDtoRequest(forestilling.getFilm());
        this.sal = new SalDtoRequest(forestilling.getSal());
        this.sæder = forestilling.getSæder().stream().map(SædeDtoRequest::new).collect(Collectors.toSet());
        this.tidspunkt = forestilling.getTidspunkt();
    }
}

package dat3.dto;

import dat3.entity.Forestilling;
import lombok.*;


import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class ForestillingDtoResponse {
    private int id;
    private BiografDtoRequest biograf;
    private FilmDtoRequest film;
    private SalDtoRequest sal;
    private Set<SædeDto> sæder;
    private LocalDateTime tidspunkt;


    public ForestillingDtoResponse(Forestilling forestilling) {
        this.id = forestilling.getId();
        this.biograf = new BiografDtoRequest(forestilling.getBiograf());
        this.film = new FilmDtoRequest(forestilling.getFilm());
        this.sal = new SalDtoRequest(forestilling.getSal());
        this.sæder = forestilling.getSæder().stream().map(SædeDto::new).collect(Collectors.toSet());
        this.tidspunkt = forestilling.getTidspunkt();
    }


}

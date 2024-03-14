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
        private BiografDtoResponse biograf;
        private FilmDtoResponse film;
        private SalDtoResponse sal;
        private Set<SædeDto> sæder;
        private LocalDateTime tidspunkt;


        public ForestillingDtoResponse(Forestilling forestilling) {
            this.id = forestilling.getId();
            this.biograf = new BiografDtoResponse(forestilling.getBiograf());
            this.film = new FilmDtoResponse(forestilling.getFilm());
            this.sal = new SalDtoResponse(forestilling.getSal());
            this.sæder = forestilling.getSæder().stream().map(SædeDto::new).collect(Collectors.toSet());
            this.tidspunkt = forestilling.getTidspunkt();
        }


}

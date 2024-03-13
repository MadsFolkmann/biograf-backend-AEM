package dat3.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import dat3.entity.Biograf;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class BiografDtoResponse {
    private Integer id;
    private String navn;
    private String adresse;
    private Integer antalSale;
    private List<SalDtoResponse> sale;

    private LocalDateTime created;
    private LocalDateTime edited;

    public BiografDtoResponse(Biograf biograf) {
        this.id = biograf.getId();
        this.navn = biograf.getNavn();
        this.adresse = biograf.getAdresse();
        this.antalSale = biograf.getAntalSale();

    }
}

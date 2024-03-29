package dat3.dto;

import dat3.entity.Biograf;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class BiografDtoResponse {
    private int id;
    private String navn;
    private String adresse;
    private int antalSale;

    private LocalDateTime created;
    private LocalDateTime edited;

    public BiografDtoResponse(Biograf biograf) {
        this.id = biograf.getId();
        this.navn = biograf.getNavn();
        this.adresse = biograf.getAdresse();
        this.antalSale = biograf.getAntalSale();
    }
}


package dat3.dto;

import dat3.entity.Biograf;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class BiografDtoRequest {
    private Integer id;
    private String navn;
    private String adresse;
    private Integer antalSale;
    private List<SalDtoResponse> sale;

    public BiografDtoRequest(Biograf biograf) {
        this.id = biograf.getId();
        this.navn = biograf.getNavn();
        this.adresse = biograf.getAdresse();
        this.antalSale = biograf.getAntalSale();

    }
}
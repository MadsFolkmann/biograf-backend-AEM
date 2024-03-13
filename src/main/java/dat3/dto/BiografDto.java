package dat3.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import dat3.entity.Biograf;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BiografDto {
    private Integer id;
    private String navn;
    private String adresse;
    private Integer antalSale;
    private List<SalDto> sale;

    private LocalDateTime created;
    private LocalDateTime edited;

    public BiografDto(Biograf biograf) {
        this.id = biograf.getId();
        this.navn = biograf.getNavn();
        this.adresse = biograf.getAdresse();
        this.antalSale = biograf.getAntalSale();

        }

    public Biograf toEntity() {
        Biograf biograf = new Biograf();
        biograf.setId(this.id);
        biograf.setNavn(this.navn);
        biograf.setAdresse(this.adresse);
        biograf.setAntalSale(this.antalSale);
        // assuming that SalDto has a toEntity() method
        biograf.setSale(this.sale.stream().map(SalDto::toEntity).collect(Collectors.toList()));
        return biograf;
    }

    }

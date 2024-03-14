package dat3.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import dat3.entity.Sæde;
import dat3.enums.SædeType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SædeDtoResponse {
    private Integer id;
    private Integer række;
    private Integer sædeNummer;
    private SædeType sædeType;
    private Double pris;
    private Boolean optaget;

    public SædeDtoResponse(Sæde sæde) {
        this.id = sæde.getId();
        this.række = sæde.getRække();
        this.sædeNummer = sæde.getSædeNummer();
        this.sædeType = sæde.getSædeType();
        this.pris = sæde.getPris();
        this.optaget = sæde.isOptaget();
    }

//    public Sæde toEntity() {
//        Sæde sæde = new Sæde();
//        sæde.setId(this.id);
//        sæde.setRække(this.række);
//        sæde.setSædeNummer(this.sædeNummer);
//        sæde.setSædeType(this.sædeType);
//        sæde.setPris(this.pris);
//        sæde.setOptaget(this.optaget);
//        return sæde;
//    }
}

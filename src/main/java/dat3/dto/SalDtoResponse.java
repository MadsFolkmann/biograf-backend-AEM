package dat3.dto;

import dat3.entity.Sal;
import dat3.enums.SalType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class  SalDtoResponse {
    private int id;
    private int nummer;
    private int antalRækker;
    private int antalSæderPrRække;
    private SalType salType;
    private BiografDtoRequest biograf;

    public SalDtoResponse(Sal sal) {
        this.id = sal.getId();
        this.nummer = sal.getNummer();
        this.antalRækker = sal.getAntalRækker();
        this.antalSæderPrRække = sal.getAntalSæderPrRække();
        this.salType = sal.getSalType();
        this.biograf = new BiografDtoRequest(sal.getBiograf());
    }
}

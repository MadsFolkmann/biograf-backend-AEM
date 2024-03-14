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
@AllArgsConstructor
public class SalDtoRequest {

    private Integer id;
    private Integer nummer;
    private Integer antalRækker;
    private Integer antalSæderPrRække;
    private SalType salType;

    public SalDtoRequest(Sal sal) {
        this.id = sal.getId();
        this.nummer = sal.getNummer();
        this.antalRækker = sal.getAntalRækker();
        this.antalSæderPrRække = sal.getAntalSæderPrRække();
        this.salType = sal.getSalType();


    }
}
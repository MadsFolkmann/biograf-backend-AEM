package dat3.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import dat3.entity.Sal;
import dat3.enums.SalType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SalDto {
    private Integer id;
    private Integer nummer;
    private Integer antalRækker;
    private Integer antalSæderPrRække;
    private SalType salType;
    private List<SædeDto> sæder; // SædeDto skal oprettes tilsvarende

    public SalDto(Sal sal, boolean includeAll) {
        this.id = sal.getId();
        this.nummer = sal.getNummer();
        this.antalRækker = sal.getAntalRækker();
        this.antalSæderPrRække = sal.getAntalSæderPrRække();
        this.salType = sal.getSalType();


    }
}

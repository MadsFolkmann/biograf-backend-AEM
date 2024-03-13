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

    public SalDto(Sal sal) {
        this.id = sal.getId();
        this.nummer = sal.getNummer();
        this.antalRækker = sal.getAntalRækker();
        this.antalSæderPrRække = sal.getAntalSæderPrRække();
        this.salType = sal.getSalType();

    }

    public Sal toEntity() {
        Sal sal = new Sal();
        sal.setId(this.id);
        sal.setNummer(this.nummer);
        sal.setAntalRækker(this.antalRækker);
        sal.setAntalSæderPrRække(this.antalSæderPrRække);
        sal.setSalType(this.salType);
        return sal;
    }
}

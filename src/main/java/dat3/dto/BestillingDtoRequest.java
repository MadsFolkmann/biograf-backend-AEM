package dat3.dto;

import dat3.entity.Bestilling;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class BestillingDtoRequest {
    private int id;
    private String name;
    private String email;
    private ForestillingDtoRequest forestilling;
    private List<SædeDtoRequest> sæder;
    private double pristotal;
    private LocalDateTime reservationstidspunkt;
    private boolean betalt;

    public BestillingDtoRequest(Bestilling bestiling){
        this.id = bestiling.getId();
        this.name = bestiling.getNavn();
        this.email = bestiling.getEmail();
        this.forestilling = new ForestillingDtoRequest(bestiling.getForestilling());
        this.sæder = bestiling.getSæder().stream().map(SædeDtoRequest::new).collect(Collectors.toList());
        this.pristotal = bestiling.getPristotal();
        this.reservationstidspunkt = bestiling.getReservationstidspunkt();
        this.betalt = bestiling.isBetalt();
    }

}

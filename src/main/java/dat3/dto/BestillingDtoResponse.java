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
public class BestillingDtoResponse {
    private int id;
    private String navn;
    private String email;
    private ForestillingDtoRequestUdenSæder forestilling;
    private List<SædeDtoRequest> sæder;
    private double pristotal;
    private LocalDateTime reservationstidspunkt;
    private boolean betalt;


    public BestillingDtoResponse(Bestilling bestiling){
        this.id = bestiling.getId();
        this.navn = bestiling.getNavn();
        this.email = bestiling.getEmail();
        this.forestilling = new ForestillingDtoRequestUdenSæder(bestiling.getForestilling());
        this.sæder = bestiling.getSæder().stream().map(SædeDtoRequest::new).collect(Collectors.toList());
        this.pristotal = bestiling.getPristotal();
        this.reservationstidspunkt = bestiling.getReservationstidspunkt();
        this.betalt = bestiling.isBetalt();
    }
}

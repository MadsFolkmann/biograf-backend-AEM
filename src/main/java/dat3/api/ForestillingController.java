package dat3.api;

import dat3.dto.ForestillingDtoRequest;
import dat3.dto.ForestillingDtoResponse;
import dat3.service.ForestillingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/forestilling")
public class ForestillingController {
    private ForestillingService forestillingService;

    public ForestillingController(ForestillingService forestillingService) {
        this.forestillingService = forestillingService;
    }

    @GetMapping
    public List<ForestillingDtoResponse> getAllForestillinger() {
        return forestillingService.getAllForestillinger();
    }

    @GetMapping(path ="/{id}")
    public ForestillingDtoResponse getForestillingById(@PathVariable int id) {
        return forestillingService.getForestillingById(id);
    }

    @GetMapping(path= "/film/{filmId}")
    public List<ForestillingDtoResponse> getForestillingerByFilmId(@PathVariable int filmId) {
        return forestillingService.getForestillingerByFilmId(filmId);
    }

    @PostMapping
    public ForestillingDtoResponse addForestilling(@RequestBody ForestillingDtoRequest forestillingDtoRequest) {
        return forestillingService.addForestilling(forestillingDtoRequest);
    }

    @PutMapping(path = "/{id}")
    public ForestillingDtoResponse editForestilling(@RequestBody ForestillingDtoRequest forestillingDtoRequest, @PathVariable int id) {
        return forestillingService.editForestilling(forestillingDtoRequest, id);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<String> deleteForestilling(@PathVariable int id) {
        forestillingService.deleteForestilling(id);
        return ResponseEntity.ok("Forestilling med id " + id + " er blevet slettet");
    }
}

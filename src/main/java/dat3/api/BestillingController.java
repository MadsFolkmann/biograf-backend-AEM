package dat3.api;

import dat3.dto.BestillingDtoRequest;
import dat3.dto.BestillingDtoResponse;
import dat3.service.BestillingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bestilling")
public class BestillingController {

    BestillingService bestillingService;

    public BestillingController(BestillingService bestillingService) {
        this.bestillingService = bestillingService;
    }

    @GetMapping
    public List<BestillingDtoResponse> getAllBestillinger() {
        return bestillingService.getAllBestillinger();
    }

    @GetMapping(path ="/{id}")
    public BestillingDtoResponse getBestillingById(@PathVariable int id) {
        return bestillingService.getBestillingById(id);
    }

    @PostMapping
    public BestillingDtoResponse addBestilling(@RequestBody BestillingDtoRequest bestillingDtoRequest) {
        return bestillingService.addBestilling(bestillingDtoRequest);
    }

    @PutMapping(path = "/{id}")
    public BestillingDtoResponse editBestilling(@RequestBody BestillingDtoRequest bestillingDtoRequest, @PathVariable int id) {
        return bestillingService.editBestilling(bestillingDtoRequest, id);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<String> deleteBestilling(@PathVariable int id) {
        bestillingService.deleteBestilling(id);
        return ResponseEntity.ok("Bestilling med id " + id + " er blevet slettet");
    }



}

package dat3.service;

import dat3.dto.BestillingDtoRequest;
import dat3.dto.BestillingDtoResponse;
import dat3.entity.*;
import dat3.repository.BestillingRepository;
import dat3.repository.ForestillingRepository;
import dat3.repository.SædeRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BestillingService {

    BestillingRepository bestillingRepository;
    ForestillingRepository forestillingRepository;
    SædeRepository sædeRepository;

    public BestillingService(BestillingRepository bestillingRepository, ForestillingRepository forestillingRepository, SædeRepository sædeRepository) {
        this.bestillingRepository = bestillingRepository;
        this.forestillingRepository = forestillingRepository;
        this.sædeRepository = sædeRepository;
    }

/**
     * Gets all bestillinger
     * @return A list of bestillinger
     */
    public List<BestillingDtoResponse> getAllBestillinger() {
        return bestillingRepository.findAll().stream().map(BestillingDtoResponse::new).collect(Collectors.toList());
    }
/**
     * Gets a bestilling by id
     * @param id The id of the bestilling
     * @return The bestilling
     */
    public BestillingDtoResponse getBestillingById(int id) {
        return new BestillingDtoResponse(bestillingRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Bestilling ikke fundet")));
    }
/**
     * Adds a bestilling
     * @param request The request containing the bestilling data
     * @return The added bestilling
     */
    public BestillingDtoResponse addBestilling(BestillingDtoRequest request) {
        Bestilling newBestilling =  new Bestilling();
        updateBestilling(newBestilling, request);
        bestillingRepository.save(newBestilling);
        return new BestillingDtoResponse(newBestilling);
    }
/**
     * Edits a bestilling
     * @param request The request containing the bestilling data
     * @param id The id of the bestilling
     * @return The edited bestilling
     */
    public BestillingDtoResponse editBestilling(BestillingDtoRequest request, int id) {
        Bestilling bestilling = bestillingRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Bestilling ikke fundet"));
        updateBestilling(bestilling, request);
        bestillingRepository.save(bestilling);
        return new BestillingDtoResponse(bestilling);
    }
/**
     * Deletes a bestilling
     * @param id The id of the bestilling
     * @return A response entity
     */
    public ResponseEntity deleteBestilling(int id) {
        bestillingRepository.deleteById(id);
        return new ResponseEntity(HttpStatus.OK);
    }
/**
     * Updates a bestilling with the data from a request
     * @param bestilling The bestilling to update
     * @param request The request containing the new data
     */
    public void updateBestilling(Bestilling bestilling, BestillingDtoRequest request) {
        Forestilling forestilling = forestillingRepository.findById(request.getForestilling().getId())
                .orElseThrow(() -> new RuntimeException("Forestilling ikke fundet"));
        List<Sæde> sæder = request.getSæder().stream().map(sædeDto -> {
                    Sæde sæde = sædeRepository.findById(sædeDto.getId())
                            .orElseThrow(() -> new RuntimeException("Sæde ikke fundet"));
                    sæde.setOptaget(true);
                    sædeRepository.save(sæde);
                    return sæde;
                })
                .collect(Collectors.toList());

        bestilling.setNavn(request.getNavn());
        bestilling.setEmail(request.getEmail());
        bestilling.setForestilling(forestilling);
        bestilling.setSæder(sæder);
        bestilling.setPristotal(request.getPristotal());
        bestilling.setReservationstidspunkt(request.getReservationstidspunkt());
        bestilling.setBetalt(request.isBetalt());
    }


}

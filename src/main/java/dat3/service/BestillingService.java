package dat3.service;

import dat3.dto.BestillingDtoRequest;
import dat3.dto.BestillingDtoResponse;
import dat3.entity.*;
import dat3.repository.BestillingRepository;
import dat3.repository.ForestillingRepository;
import dat3.repository.SædeRepository;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
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

    public List<BestillingDtoResponse> getAllBestillinger() {
        return bestillingRepository.findAll().stream().map(BestillingDtoResponse::new).collect(Collectors.toList());
    }

    public BestillingDtoResponse getBestillingById(int id) {
        return new BestillingDtoResponse(bestillingRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Bestilling ikke fundet")));
    }

    public BestillingDtoResponse addBestilling(BestillingDtoRequest request) {
        Bestilling newBestilling =  new Bestilling();
        updateBestilling(newBestilling, request);
        bestillingRepository.save(newBestilling);
        return new BestillingDtoResponse(newBestilling);
    }

    public BestillingDtoResponse editBestilling(BestillingDtoRequest request, int id) {
        Bestilling bestilling = bestillingRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Bestilling ikke fundet"));
        updateBestilling(bestilling, request);
        bestillingRepository.save(bestilling);
        return new BestillingDtoResponse(bestilling);
    }

    public ResponseEntity deleteBestilling(int id) {
        bestillingRepository.deleteById(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    public void updateBestilling(Bestilling bestilling, BestillingDtoRequest request) {

        List<Forestilling> forestillinger = request.getForestilling().stream().map(forestilling -> forestillingRepository.findById(forestilling.getId()).orElseThrow(() -> new RuntimeException("Forestilling ikke fundet"))).collect(Collectors.toList());
        List<Sæde> sæder = request.getSæder().stream().map(sæde -> sædeRepository.findById(sæde.getId()).orElseThrow(() -> new RuntimeException("Sæde ikke fundet"))).collect(Collectors.toList());

        bestilling.setNavn(request.getName());
        bestilling.setEmail(request.getEmail());
        bestilling.setForestilling(forestillinger);
        bestilling.setSæder(sæder);
        bestilling.setPristotal(request.getPristotal());
        bestilling.setReservationstidspunkt(request.getReservationstidspunkt());
        bestilling.setBetalt(request.isBetalt());

    }


}

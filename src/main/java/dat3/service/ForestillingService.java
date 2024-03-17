package dat3.service;

import dat3.dto.*;
import dat3.entity.*;
import dat3.repository.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ForestillingService {
    ForestillingRepository forestillingRepository;
    BiografRepository biografRepository;
    FilmRepository filmRepository;
    SalRepository salRepository;
    SædeRepository sædeRepository;

    public ForestillingService(ForestillingRepository forestillingRepository, BiografRepository biografRepository, FilmRepository filmRepository, SalRepository salRepository, SædeRepository sædeRepository) {
        this.forestillingRepository = forestillingRepository;
        this.biografRepository = biografRepository;
        this.filmRepository = filmRepository;
        this.salRepository = salRepository;
        this.sædeRepository = sædeRepository;
    }

    public List<ForestillingDtoResponse> getAllForestillinger() {
        return forestillingRepository.findAll().stream().map(ForestillingDtoResponse::new).collect(Collectors.toList());
    }

    public ForestillingDtoResponse getForestillingById(int id) {
        return new ForestillingDtoResponse(forestillingRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Forestilling ikke fundet")));
    }

    public List<ForestillingDtoResponse> getForestillingerByFilmId(int filmId) {
        return forestillingRepository.findAll().stream().filter(forestilling -> forestilling.getFilm().getId() == filmId).map(ForestillingDtoResponse::new).collect(Collectors.toList());
    }

    public ForestillingDtoResponse addForestilling(ForestillingDtoRequest request) {
        Forestilling newForestilling = new Forestilling();
        updateForestilling(newForestilling, request);
        newForestilling.setTidspunkt(request.getTidspunkt());
        forestillingRepository.save(newForestilling);
        return new ForestillingDtoResponse(newForestilling);
    }

    public ForestillingDtoResponse editForestilling(ForestillingDtoRequest request, int id) {
        Forestilling forestilling = forestillingRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Forestilling ikke fundet"));
        updateForestilling(forestilling, request);
        forestillingRepository.save(forestilling);
        return new ForestillingDtoResponse(forestilling);
    }

    public ResponseEntity deleteForestilling(int id) {
        forestillingRepository.deleteById(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    public void updateForestilling(Forestilling original, ForestillingDtoRequest request) {
        Biograf biograf = biografRepository.findById(request.getBiograf().getId())
                .orElseThrow(() -> new RuntimeException("Biograf ikke fundet"));
        Film film = filmRepository.findById(request.getFilm().getId())
                .orElseThrow(() -> new RuntimeException("Film ikke fundet"));
        Sal sal = salRepository.findById(request.getSal().getId())
                .orElseThrow(() -> new RuntimeException("Sal ikke fundet"));
        Set<Sæde> sæder = request.getSæder().stream().map(sæde -> sædeRepository.findById(sæde.getId()).orElseThrow(() -> new RuntimeException("Sæde ikke fundet"))).collect(Collectors.toSet());

        original.setBiograf(biograf);
        original.setFilm(film);
        original.setSal(sal);
        original.setSæder(sæder);
        original.setTidspunkt(request.getTidspunkt());
    }
}

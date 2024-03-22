package dat3.service;

import dat3.dto.*;
import dat3.entity.*;
import dat3.enums.SædeType;
import dat3.repository.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;
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

    /**
     * Gets all forestillinger
     * @return A list of forestillinger
     */
    public List<ForestillingDtoResponse> getAllForestillinger() {
        return forestillingRepository.findAll().stream().map(ForestillingDtoResponse::new).collect(Collectors.toList());
    }
/**
     * Gets a forestilling by id
     * @param id The id of the forestilling
     * @return The forestilling
     */
    public ForestillingDtoResponse getForestillingById(int id) {
        return new ForestillingDtoResponse(forestillingRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Forestilling ikke fundet")));
    }
/**
     * Gets all forestillinger for a film
     * @param filmId The id of the film
     * @return A list of forestillinger
     */
    public List<ForestillingDtoResponse> getForestillingerByFilmId(int filmId) {
        return forestillingRepository.findAll().stream().filter(forestilling -> forestilling.getFilm().getId() == filmId).map(ForestillingDtoResponse::new).collect(Collectors.toList());
    }
/**
     * Adds a forestilling
     * @param request The request containing the forestilling data
     * @return The added forestilling
     */
    public ForestillingDtoResponse addForestilling(ForestillingDtoRequest request) {
        Forestilling newForestilling = new Forestilling();
        updateForestilling(newForestilling, request);
        forestillingRepository.save(newForestilling);
        return new ForestillingDtoResponse(newForestilling);
    }
/**
     * Edits a forestilling
     * @param request The request containing the new forestilling data
     * @param id The id of the forestilling to edit
     * @return The edited forestilling
     */
    public ForestillingDtoResponse editForestilling(ForestillingDtoRequest request, int id) {
        Forestilling forestilling = forestillingRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Forestilling ikke fundet"));
        updateForestilling(forestilling, request);
        forestillingRepository.save(forestilling);
        return new ForestillingDtoResponse(forestilling);
    }
/**
     * Deletes a forestilling
     * @param id The id of the forestilling to delete
     * @return A response entity
     */
    public ResponseEntity deleteForestilling(int id) {
        forestillingRepository.deleteById(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    /**
     * Updates a forestilling with the values from the request
     * @param original The forestilling to update
     * @param request The request containing the new values
     */
    public void updateForestilling(Forestilling original, ForestillingDtoRequest request) {
        Biograf biograf = biografRepository.findById(request.getBiograf().getId())
                .orElseThrow(() -> new RuntimeException("Biograf ikke fundet"));
        Film film = filmRepository.findById(request.getFilm().getId())
                .orElseThrow(() -> new RuntimeException("Film ikke fundet"));
        Sal sal = salRepository.findById(request.getSal().getId())
                .orElseThrow(() -> new RuntimeException("Sal ikke fundet"));

        original.setBiograf(biograf);
        original.setFilm(film);
        original.setSal(sal);
        original.setTidspunkt(request.getTidspunkt());

        if (original.getId() == 0) {
            Set<Sæde> sæder = generateSæderForNewForestilling(sal);
            original.setSæder(sæder);
        }
    }

    /**
     * Generates sæder for a new forestilling based on the sal
     * @param sal The sal to generate sæder for
     * @return A set of sæder
     */
    private Set<Sæde> generateSæderForNewForestilling(Sal sal) {
        Set<Sæde> sæder = new HashSet<>();
        for (int række = 1; række <= sal.getAntalRækker(); række++) {
            for (int sædeNummer = 1; sædeNummer <= sal.getAntalSæderPrRække(); sædeNummer++) {
                SædeType sædeType;
                if (række <= 2) {
                    sædeType = SædeType.COWBOY;
                } else if (række == sal.getAntalRækker()) {
                    sædeType = SædeType.VIP;
                } else {
                    sædeType = SædeType.STANDARD;
                }
                double pris;
                if (sædeType == SædeType.COWBOY) {
                    pris = 50.0;
                } else if (sædeType == SædeType.VIP) {
                    pris = 150.0;
                } else {
                    pris = 100.0;
                }
                Sæde sæde = new Sæde(række, sædeNummer, sædeType, pris, false);
                sædeRepository.save(sæde);
                sæder.add(sæde);
            }
        }
        return sæder;
    }
}


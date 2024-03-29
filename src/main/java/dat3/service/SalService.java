package dat3.service;

import dat3.dto.SalDtoRequest;
import dat3.dto.SalDtoResponse;
import dat3.entity.Biograf;
import dat3.entity.Sal;
import dat3.repository.BiografRepository;
import dat3.repository.SalRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class SalService {
    private final SalRepository salRepository;
    private final BiografRepository biografRepository;

    public SalService(SalRepository salRepository, BiografRepository biografRepository) {
        this.salRepository = salRepository;
        this.biografRepository = biografRepository;
    }
/**
     * Gets all sale
     * @return A list of sale
     */
    public List<SalDtoResponse> getAllSale() {
        return salRepository.findAll().stream().map(SalDtoResponse::new).collect(Collectors.toList());
    }
/**
     * Gets a sal by id
     * @param id The id of the sal
     * @return The sal
     */
    public SalDtoResponse getSalById(int id) {
        Sal sal = salRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Sal not found"));
        return new SalDtoResponse(sal);
    }
/**
     * Gets all sale for a biograf
     * @param biografId The id of the biograf
     * @return A list of sale
     */
    public List<SalDtoResponse> getSaleByBiograf(int biografId) {
        return salRepository.findAll().stream()
                .filter(sal -> sal.getBiograf().getId() == biografId)
                .map(SalDtoResponse::new)
                .collect(Collectors.toList());
    }
/**
     * Adds a sal
     * @param request The request containing the sal data
     * @return The added sal
     */
    public SalDtoResponse addSal(SalDtoRequest request) {
        Sal newSal = new Sal();
        updateSal(newSal, request);
        salRepository.save(newSal);
        return new SalDtoResponse(newSal);
    }
/**
     * Edits a sal
     * @param request The request containing the sal data
     * @param id The id of the sal
     * @return The edited sal
     */
    public SalDtoResponse editSal(SalDtoRequest request, int id) {
        Sal sal = salRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Sal not found"));
        updateSal(sal, request);
        salRepository.save(sal);
        return new SalDtoResponse(sal);
    }
/**
     * Updates a sal with the data from a request
     * @param original The sal to update
     * @param request The request containing the new data
     */
    public void updateSal(Sal original, SalDtoRequest request) {
        Biograf biograf = biografRepository.findById(request.getBiograf().getId()).orElseThrow(() -> new NoSuchElementException("Biograf not found"));

        original.setBiograf(biograf);
        original.setAntalRækker(request.getAntalRækker());
        original.setAntalSæderPrRække(request.getAntalSæderPrRække());
        original.setNummer(request.getNummer());
        original.setSalType(request.getSalType());
    }
/**
     * Deletes a sal
     * @param id The id of the sal
     * @return A response entity
     */
    public ResponseEntity<Void> deleteSal(int id) {
        salRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}

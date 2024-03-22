package dat3.service;

import dat3.dto.BiografDtoRequest;
import dat3.dto.BiografDtoResponse;
import dat3.entity.Biograf;
import dat3.repository.BiografRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BiografService {
    private final BiografRepository biografRepository;

    public BiografService(BiografRepository biografRepository) {
        this.biografRepository = biografRepository;
    }
/**
     * Gets all biografer
     * @return A list of biografer
     */
    public List<BiografDtoResponse> getAllBiografer() {
        List<Biograf> biografer = biografRepository.findAll();
        return biografer.stream()
                .map(BiografDtoResponse::new)
                .collect(Collectors.toList());
    }
/**
     * Gets a biograf by id
     * @param id The id of the biograf
     * @return The biograf
     */
    public BiografDtoResponse getBiografById(int id) {
        Biograf biograf = biografRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Biograf ikke fundet"));
        return new BiografDtoResponse(biograf);
    }
/**
     * Adds a biograf
     * @param request The request containing the biograf data
     * @return The added biograf
     */
    public BiografDtoResponse addBiograf(BiografDtoRequest request) {
        Biograf newBiograf = new Biograf();
        updateBiograf(newBiograf, request);
        biografRepository.save(newBiograf);
        return new BiografDtoResponse(newBiograf);
    }
/**
     * Edits a biograf
     * @param request The request containing the biograf data
     * @param id The id of the biograf
     * @return The edited biograf
     */
    public BiografDtoResponse editBiograf(BiografDtoRequest request, int id) {
        Biograf biograf = biografRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Biograf ikke fundet"));
        updateBiograf(biograf, request);
        biografRepository.save(biograf);
        return new BiografDtoResponse(biograf);
    }
/**
     * Deletes a biograf
     * @param id The id of the biograf
     * @return A response entity
     */
    public ResponseEntity<Void> deleteBiograf(int id) {
        Biograf biograf = biografRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Biograf ikke fundet"));
        biografRepository.delete(biograf);
        return ResponseEntity.ok().build();
    }
/**
     * Updates a biograf with the data from a request
     * @param biograf The biograf to update
     * @param request The request containing the new data
     */
    private void updateBiograf(Biograf biograf, BiografDtoRequest request) {
        biograf.setNavn(request.getNavn());
        biograf.setAdresse(request.getAdresse());
        biograf.setAntalSale(request.getAntalSale());
    }
}

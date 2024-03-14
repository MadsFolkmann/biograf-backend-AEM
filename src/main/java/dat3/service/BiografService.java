package dat3.service;

import dat3.dto.BiografDtoRequest;
import dat3.dto.BiografDtoResponse;
import dat3.entity.Biograf;
import dat3.repository.BiografRepository;
import org.springframework.http.HttpStatus;
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

    public List<BiografDtoResponse> getAllBiografer() {
        List<Biograf> biografer = biografRepository.findAll();
        return biografer.stream()
                .map(BiografDtoResponse::new)
                .collect(Collectors.toList());
    }

    public BiografDtoResponse getBiografById(int id) {
        Biograf biograf = biografRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Biograf ikke fundet"));
        return new BiografDtoResponse(biograf);
    }

    public BiografDtoResponse addBiograf(BiografDtoRequest request) {
        Biograf newBiograf = new Biograf();
        updateBiograf(newBiograf, request);
        biografRepository.save(newBiograf);
        return new BiografDtoResponse(newBiograf);
    }

    public BiografDtoResponse editBiograf(BiografDtoRequest request, int id) {
        Biograf biograf = biografRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Biograf ikke fundet"));
        updateBiograf(biograf, request);
        biografRepository.save(biograf);
        return new BiografDtoResponse(biograf);
    }

    public ResponseEntity<Void> deleteBiograf(int id) {
        Biograf biograf = biografRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Biograf ikke fundet"));
        biografRepository.delete(biograf);
        return ResponseEntity.ok().build();
    }

    private void updateBiograf(Biograf biograf, BiografDtoRequest request) {
        biograf.setNavn(request.getNavn());
        biograf.setAdresse(request.getAdresse());
        biograf.setAntalSale(request.getAntalSale());
    }
}

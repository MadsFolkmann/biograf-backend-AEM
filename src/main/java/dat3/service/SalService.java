package dat3.service;

import dat3.dto.SalDtoRequest;
import dat3.dto.SalDtoResponse;
import dat3.entity.Sal;
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

    public SalService(SalRepository salRepository) {
        this.salRepository = salRepository;
    }

    public List<SalDtoResponse> getAllSale() {
        return salRepository.findAll().stream().map(SalDtoResponse::new).collect(Collectors.toList());
    }

    public SalDtoResponse getSalById(int id) {
        Sal sal = salRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Sal not found"));
        return new SalDtoResponse(sal);
    }

    public SalDtoResponse addSal(SalDtoRequest request) {
        Sal newSal = new Sal();
        updateSal(newSal, request);
        salRepository.save(newSal);
        return new SalDtoResponse(newSal);
    }

    public SalDtoResponse editSal(SalDtoRequest request, int id) {
        Sal sal = salRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Sal not found"));
        updateSal(sal, request);
        salRepository.save(sal);
        return new SalDtoResponse(sal);
    }

    public void updateSal(Sal original, SalDtoRequest request) {
        original.setAntalRækker(request.getAntalRækker());
        original.setAntalSæderPrRække(request.getAntalSæderPrRække());
    }

    public ResponseEntity<Void> deleteSal(int id) {
        salRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}

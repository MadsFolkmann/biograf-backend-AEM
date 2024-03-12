package dat3.service;

import dat3.dto.BiografDto;
import dat3.entity.Biograf;
import dat3.repository.BiografRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BiografService {
    BiografRepository biografRepository;


    public BiografService(BiografRepository biografRepository) {
        this.biografRepository = biografRepository;
    }

    public List<BiografDto> getAllBiografer() {
        List<Biograf> biografer =  biografRepository.findAll();
        return biografer.stream().map(biograf -> new BiografDto(biograf, true)) // Brug FilmDto-konstruktøren, der mapper alle filmoplysninger
                .toList();
    }

    public BiografDto getBiografById(int idInt) {
        Biograf biograf = biografRepository.findById(idInt).orElseThrow(() ->
                new RuntimeException("Biograf ikke fundet"));
        return new BiografDto(biograf, false);
    }

    public BiografDto addBiograf(BiografDto request) {
        Biograf newBiograf = new Biograf();
        updateBiograf(newBiograf, request);
        biografRepository.save(newBiograf);
        return new BiografDto(newBiograf, false);
    }

    public void updateBiograf(Biograf original, BiografDto r) {
        original.setNavn(r.getNavn());
        original.setAdresse(r.getAdresse());
        original.setAntalSale(r.getAntalSale());
    }


    public BiografDto editBiograf(BiografDto request, int id) {
        Biograf biograf = biografRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Biograf ikke fundet"));
        updateBiograf(biograf, request);
        biografRepository.save(biograf);
        return new BiografDto(biograf, false);
    }

    public ResponseEntity deleteBiograf(int id) {
        Biograf biograf = biografRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Biograf ikke fundet"));
        return new ResponseEntity(HttpStatus.OK);
    }
}

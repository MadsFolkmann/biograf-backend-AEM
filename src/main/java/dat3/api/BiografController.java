package dat3.api;

import dat3.dto.BiografDtoRequest;
import dat3.dto.BiografDtoResponse;
import dat3.service.BiografService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/biograf")
public class BiografController {

    private final BiografService biografService;

    public BiografController(BiografService biografService) {
        this.biografService = biografService;
    }

    @GetMapping
    public List<BiografDtoResponse> getAllBiografer() {
        return biografService.getAllBiografer();
    }

    @GetMapping(path = "/{id}")
    public BiografDtoResponse getBiografById(@PathVariable Integer id) {
        return biografService.getBiografById(id);
    }

    @PostMapping
    public BiografDtoResponse addBiograf(@RequestBody BiografDtoRequest request) {
        return biografService.addBiograf(request);
    }

    @PutMapping(path = "/{id}")
    public BiografDtoResponse editBiograf(@RequestBody BiografDtoRequest request, @PathVariable Integer id) {
        return biografService.editBiograf(request, id);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteBiograf(@PathVariable Integer id) {
        return biografService.deleteBiograf(id);
    }
}

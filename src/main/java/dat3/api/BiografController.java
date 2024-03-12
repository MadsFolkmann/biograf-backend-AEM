package dat3.api;

import dat3.dto.BiografDto;
import dat3.service.BiografService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/biograf")
public class BiografController {
    private BiografService biografService;

    public BiografController(BiografService biografService) {
        this.biografService = biografService;
    }

    @GetMapping
    public List<BiografDto> getAllBiografer() {
        return biografService.getAllBiografer();
    }

    @GetMapping(path ="/{id}")
    public BiografDto getBiografById(int id) {
        return biografService.getBiografById(id);
    }

    @PostMapping
    public BiografDto addBiograf(BiografDto request) {
        return biografService.addBiograf(request);
    }

    @PutMapping(path = "/{id}")
    public BiografDto editBiograf(BiografDto request,int id) {
        return biografService.editBiograf(request,id);
    }

    @DeleteMapping(path = "/{id}")
    public void deleteBiograf(int id) {
        biografService.deleteBiograf(id);
    }
}

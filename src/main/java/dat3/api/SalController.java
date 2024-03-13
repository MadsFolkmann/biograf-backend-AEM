package dat3.api;

import dat3.dto.SalDtoRequest;
import dat3.dto.SalDtoResponse;
import dat3.repository.SalRepository;
import dat3.service.SalService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sal")
public class SalController {
    private SalService salService;

    public SalController(SalService salService) {
        this.salService = salService;
    }

    @GetMapping
    public List<SalDtoResponse> getAllSale() {
        return salService.getAllSale();
    }

    @GetMapping(path ="/{id}")
    public SalDtoResponse getSalById(@PathVariable Integer id) {
        return salService.getSalById(id);
    }

    @PostMapping
    public SalDtoResponse addSal(@RequestBody SalDtoRequest salDtoRequest) {
        return salService.addSal(salDtoRequest);
    }

    @PutMapping(path = "/{id}")
    public SalDtoResponse editSal(@RequestBody SalDtoRequest salDtoRequest, @PathVariable int id) {
        return salService.editSal(salDtoRequest, id);
    }

    @DeleteMapping(path = "/{id}")
    public void deleteSal(@PathVariable int id) {
        salService.deleteSal(id);
    }


}

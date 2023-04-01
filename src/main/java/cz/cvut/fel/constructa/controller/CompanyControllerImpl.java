package cz.cvut.fel.constructa.controller;

import cz.cvut.fel.constructa.controller.interfaces.CompanyController;
import cz.cvut.fel.constructa.dto.request.CompanyRequest;
import cz.cvut.fel.constructa.dto.response.CompanyDTO;
import cz.cvut.fel.constructa.service.interfaces.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/companies")
@RequiredArgsConstructor
public class CompanyControllerImpl implements CompanyController {
    private final CompanyService companyService;

    @Override
    @ResponseStatus(code = HttpStatus.OK)
    @ResponseBody
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CompanyDTO>> getCompanies() {
        return ResponseEntity.ok().body(
                companyService.getCompanies()
        );
    }

    @Override
    @ResponseStatus(code = HttpStatus.OK)
    @ResponseBody
    @GetMapping(value = "/{companyId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CompanyDTO> getCompanys(@PathVariable Long companyId) {
        CompanyDTO company = companyService.getCompanyById(companyId);
        if (company != null) {
            return ResponseEntity.ok().body(
                    company
            );
        }
        return ResponseEntity.notFound().build();
    }

    @Override
    @ResponseStatus(code = HttpStatus.CREATED)
    @ResponseBody
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CompanyDTO> createCompany(@RequestBody CompanyRequest request) throws ParseException {
        return ResponseEntity.ok().body(
                companyService.create(request)
        );
    }

    @Override
    @ResponseStatus(code = HttpStatus.OK)
    @ResponseBody
    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CompanyDTO> editCompany(@RequestBody CompanyRequest request) throws ParseException {
        return ResponseEntity.ok().body(
                companyService.update(request)
        );
    }

    @Override
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "/{companyId}")
    public ResponseEntity<Void> deleteCompany(@PathVariable Long companyId) {
        companyService.delete(companyId);
        return ResponseEntity.noContent().build();
    }
}

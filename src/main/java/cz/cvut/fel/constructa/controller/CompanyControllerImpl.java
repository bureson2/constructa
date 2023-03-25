package cz.cvut.fel.constructa.controller;

import cz.cvut.fel.constructa.controller.interfaces.CompanyController;
import cz.cvut.fel.constructa.dto.response.CompanyDTO;
import cz.cvut.fel.constructa.mapper.CompanyMapper;
import cz.cvut.fel.constructa.model.Company;
import cz.cvut.fel.constructa.service.interfaces.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/companies")
@RequiredArgsConstructor
public class CompanyControllerImpl implements CompanyController {
    private final CompanyService companyService;
    private final CompanyMapper companyMapper;

    @ResponseStatus(code = HttpStatus.OK)
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CompanyDTO>> getCompanies() {
        List<Company> companies = companyService.getCompanies();
        return ResponseEntity.ok().body(
                companies.stream()
                        .map(companyMapper::convertToDto)
                        .collect(Collectors.toList())
        );
    }

    @ResponseStatus(code = HttpStatus.OK)
    @ResponseBody
    @GetMapping(value = "/{companyId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CompanyDTO> getCompanys(@PathVariable Long companyId) {
        Optional<Company> companyToReturn = companyService.getCompanyById(companyId);
        return companyToReturn.map(task -> ResponseEntity.ok().body(
                companyMapper.convertToDto(task)
        )).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @ResponseStatus(code = HttpStatus.CREATED)
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CompanyDTO> createCompany(@RequestBody Company newCompany) {
        Company createdCompany = companyService.create(newCompany);
        return ResponseEntity.ok().body(
                companyMapper.convertToDto(createdCompany)
        );
    }

    @ResponseStatus(code = HttpStatus.OK)
    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CompanyDTO> editCompany(@RequestBody Company updatedCompany){
        Company companyToReturn = companyService.update(updatedCompany);
        return ResponseEntity.ok().body(
                companyMapper.convertToDto(companyToReturn));
    }

    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "/{companyId}")
    public ResponseEntity<Void> deleteCompany(@PathVariable Long companyId) {
        companyService.delete(companyId);
        return ResponseEntity.noContent().build();
    }
}

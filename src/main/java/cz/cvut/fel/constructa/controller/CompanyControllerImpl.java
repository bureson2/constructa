package cz.cvut.fel.constructa.controller;

import cz.cvut.fel.constructa.controller.interfaces.CompanyController;
import cz.cvut.fel.constructa.dto.request.CompanyRequest;
import cz.cvut.fel.constructa.dto.response.CompanyDTO;
import cz.cvut.fel.constructa.mapper.CompanyMapper;
import cz.cvut.fel.constructa.model.Company;
import cz.cvut.fel.constructa.service.interfaces.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
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

    @Override
    @ResponseStatus(code = HttpStatus.OK)
    @ResponseBody
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CompanyDTO>> getCompanies() {
        List<Company> companies = companyService.getCompanies();
        return ResponseEntity.ok().body(
                companies.stream()
                        .map(companyMapper::convertToDto)
                        .collect(Collectors.toList())
        );
    }

    @Override
    @ResponseStatus(code = HttpStatus.OK)
    @ResponseBody
    @GetMapping(value = "/{companyId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CompanyDTO> getCompanys(@PathVariable Long companyId) {
        Optional<Company> companyToReturn = companyService.getCompanyById(companyId);
        return companyToReturn.map(task -> ResponseEntity.ok().body(
                companyMapper.convertToDto(task)
        )).orElseGet(() -> ResponseEntity.notFound().build());
    }
    @Override
    @ResponseStatus(code = HttpStatus.CREATED)
    @ResponseBody
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CompanyDTO> createCompany(@RequestBody CompanyRequest request) throws ParseException {
        Company createdCompany = companyService.create(request);
        return ResponseEntity.ok().body(
                companyMapper.convertToDto(createdCompany)
        );
    }
    @Override
    @ResponseStatus(code = HttpStatus.OK)
    @ResponseBody
    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CompanyDTO> editCompany(@RequestBody CompanyRequest request) throws ParseException {
        Company companyToReturn = companyService.update(request);
        return ResponseEntity.ok().body(
                companyMapper.convertToDto(companyToReturn));
    }

    @Override
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "/{companyId}")
    public ResponseEntity<Void> deleteCompany(@PathVariable Long companyId) {
        companyService.delete(companyId);
        return ResponseEntity.noContent().build();
    }
}

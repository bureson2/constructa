package cz.cvut.fel.constructa.controller.interfaces;

import cz.cvut.fel.constructa.dto.request.CompanyRequest;
import cz.cvut.fel.constructa.dto.response.CompanyDTO;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

public interface CompanyController {
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<CompanyDTO>> getCompanies();

    @GetMapping(value = "/{companyId}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<CompanyDTO> getCompanys(@PathVariable Long companyId);

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<CompanyDTO> createCompany(@RequestBody CompanyRequest request) throws ParseException;

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<CompanyDTO> editCompany(@RequestBody CompanyRequest request) throws ParseException;

    @DeleteMapping(value = "/{companyId}")
    ResponseEntity<Void> deleteCompany(@PathVariable Long companyId);
}

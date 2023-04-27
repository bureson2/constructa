package cz.cvut.fel.constructa.controller.interfaces;

import cz.cvut.fel.constructa.dto.request.CompanyRequest;
import cz.cvut.fel.constructa.dto.response.CompanyDTO;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

/**
 * The interface Company controller.
 */
@RequestMapping("/api/v1/companies")
public interface CompanyController {
    /**
     * Gets companies.
     *
     * @return the companies
     */
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<CompanyDTO>> getCompanies();

    /**
     * Gets companys.
     *
     * @param companyId the company id
     * @return the companys
     */
    @GetMapping(value = "/{companyId}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<CompanyDTO> getCompanys(@PathVariable Long companyId);

    /**
     * Create company response entity.
     *
     * @param request the request
     * @return the response entity
     * @throws ParseException the parse exception
     */
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<CompanyDTO> createCompany(@RequestBody CompanyRequest request) throws ParseException;

    /**
     * Edit company response entity.
     *
     * @param request the request
     * @return the response entity
     * @throws ParseException the parse exception
     */
    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<CompanyDTO> editCompany(@RequestBody CompanyRequest request) throws ParseException;

    /**
     * Delete company response entity.
     *
     * @param companyId the company id
     * @return the response entity
     */
    @DeleteMapping(value = "/{companyId}")
    ResponseEntity<Void> deleteCompany(@PathVariable Long companyId);
}

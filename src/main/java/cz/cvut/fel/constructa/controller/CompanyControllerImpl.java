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

/**
 * The type Company controller.
 */
@CrossOrigin
@RestController
@RequestMapping("/api/v1/companies")
@RequiredArgsConstructor
public class CompanyControllerImpl implements CompanyController {
    /**
     * The Company service.
     */
    private final CompanyService companyService;

    /**
     * Gets companies.
     *
     * @return the companies
     */
    @Override
    @ResponseStatus(code = HttpStatus.OK)
    @ResponseBody
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CompanyDTO>> getCompanies() {
        return ResponseEntity.ok().body(
                companyService.getCompanies()
        );
    }

    /**
     * Gets companys.
     *
     * @param companyId the company id
     * @return the companys
     */
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

    /**
     * Create company response entity.
     *
     * @param request the request
     * @return the response entity
     * @throws ParseException the parse exception
     */
    @Override
    @ResponseStatus(code = HttpStatus.CREATED)
    @ResponseBody
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CompanyDTO> createCompany(@RequestBody CompanyRequest request) throws ParseException {
        return ResponseEntity.ok().body(
                companyService.create(request)
        );
    }

    /**
     * Edit company response entity.
     *
     * @param request the request
     * @return the response entity
     * @throws ParseException the parse exception
     */
    @Override
    @ResponseStatus(code = HttpStatus.OK)
    @ResponseBody
    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CompanyDTO> editCompany(@RequestBody CompanyRequest request) throws ParseException {
        return ResponseEntity.ok().body(
                companyService.update(request)
        );
    }

    /**
     * Delete company response entity.
     *
     * @param companyId the company id
     * @return the response entity
     */
    @Override
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "/{companyId}")
    public ResponseEntity<Void> deleteCompany(@PathVariable Long companyId) {
        companyService.delete(companyId);
        return ResponseEntity.noContent().build();
    }
}

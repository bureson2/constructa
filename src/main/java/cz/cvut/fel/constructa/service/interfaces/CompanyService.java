package cz.cvut.fel.constructa.service.interfaces;

import cz.cvut.fel.constructa.dto.request.CompanyRequest;
import cz.cvut.fel.constructa.dto.response.CompanyDTO;

import java.text.ParseException;
import java.util.List;

/**
 * A service interface class that provides CRUD operations for companies.
 */
public interface CompanyService {
    /**
     * Creates a new company with the given details.
     *
     * @param request The details of the company to create.
     * @return The newly created company.
     * @throws ParseException if there is an error parsing the request data.
     */
    CompanyDTO create(CompanyRequest request) throws ParseException;

    /**
     * Retrieves a company by its ID.
     *
     * @param id The ID of the company to retrieve.
     * @return The company with the given ID, or null if no such company exists.
     */
    CompanyDTO getCompanyById(Long id);

    /**
     * Retrieves all companies.
     *
     * @return A list of all companies.
     */
    List<CompanyDTO> getCompanies();

    /**
     * Deletes a company by its ID.
     *
     * @param id The ID of the company to delete.
     */
    void delete(Long id);

    /**
     * Updates an existing company with the given details.
     *
     * @param request The details of the company to update.
     * @return The updated company.
     * @throws ParseException if there is an error parsing the request data.
     */
    CompanyDTO update(CompanyRequest request) throws ParseException;
}

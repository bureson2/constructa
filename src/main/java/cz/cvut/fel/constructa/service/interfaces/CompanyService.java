package cz.cvut.fel.constructa.service.interfaces;

import cz.cvut.fel.constructa.dto.request.CompanyRequest;
import cz.cvut.fel.constructa.dto.response.CompanyDTO;

import java.text.ParseException;
import java.util.List;

/**
 * The interface Company service.
 */
public interface CompanyService {
    /**
     * Create company dto.
     *
     * @param request the request
     * @return the company dto
     * @throws ParseException the parse exception
     */
    CompanyDTO create(CompanyRequest request) throws ParseException;

    /**
     * Gets company by id.
     *
     * @param id the id
     * @return the company by id
     */
    CompanyDTO getCompanyById(Long id);

    /**
     * Gets companies.
     *
     * @return the companies
     */
    List<CompanyDTO> getCompanies();

    /**
     * Delete.
     *
     * @param id the id
     */
    void delete(Long id);

    /**
     * Update company dto.
     *
     * @param request the request
     * @return the company dto
     * @throws ParseException the parse exception
     */
    CompanyDTO update(CompanyRequest request) throws ParseException;
}

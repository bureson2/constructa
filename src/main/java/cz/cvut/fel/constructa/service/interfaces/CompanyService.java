package cz.cvut.fel.constructa.service.interfaces;

import cz.cvut.fel.constructa.dto.request.CompanyRequest;
import cz.cvut.fel.constructa.dto.response.CompanyDTO;

import java.text.ParseException;
import java.util.List;

public interface CompanyService {
    CompanyDTO create(CompanyRequest request) throws ParseException;
    CompanyDTO getCompanyById(Long id);
    List<CompanyDTO> getCompanies();
    void delete(Long id);
    CompanyDTO update(CompanyRequest request) throws ParseException;
}

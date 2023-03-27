package cz.cvut.fel.constructa.service.interfaces;

import cz.cvut.fel.constructa.dto.request.CompanyRequest;
import cz.cvut.fel.constructa.model.Company;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;

public interface CompanyService {
    Company create(CompanyRequest request) throws ParseException;
    Optional<Company> getCompanyById(Long id);
    List<Company> getCompanies();
    void delete(Long id);
    Company update(Company updatedCompany);
}

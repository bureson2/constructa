package cz.cvut.fel.constructa.service.interfaces;

import cz.cvut.fel.constructa.model.Company;
import cz.cvut.fel.constructa.model.Project;

import java.util.List;
import java.util.Optional;

public interface CompanyService {
    Company create(Company company);
    Optional<Company> getCompanyById(Long id);
    List<Company> getCompanies();
    void delete(Long id);
    Company update(Company updatedCompany);
}

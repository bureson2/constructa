package cz.cvut.fel.constructa.service;

import cz.cvut.fel.constructa.model.Company;
import cz.cvut.fel.constructa.repository.CompanyRepository;
import cz.cvut.fel.constructa.service.interfaces.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {
    private final CompanyRepository companyDao;

    @Override
    public Company create(Company company) {
        return companyDao.save(company);
    }

    @Override
    public Optional<Company> getCompanyById(Long id) {
        return companyDao.findById(id);
    }

    @Override
    public List<Company> getCompanies() {
        return companyDao.findAll();
    }

    @Override
    public void delete(Long id) {
        companyDao.deleteById(id);
    }

    @Override
    public Company update(Company updatedCompany) {
        return companyDao.save(updatedCompany);
    }
}

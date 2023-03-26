package cz.cvut.fel.constructa.service;

import cz.cvut.fel.constructa.dto.request.CompanyRequest;
import cz.cvut.fel.constructa.mapper.CompanyMapper;
import cz.cvut.fel.constructa.model.Company;
import cz.cvut.fel.constructa.repository.CompanyRepository;
import cz.cvut.fel.constructa.service.interfaces.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {
    private final CompanyRepository companyDao;
    private final CompanyMapper companyMapper;

    @Override
    public Company create(CompanyRequest request) throws ParseException {
        Company company = companyMapper.convertToEntity(request);
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

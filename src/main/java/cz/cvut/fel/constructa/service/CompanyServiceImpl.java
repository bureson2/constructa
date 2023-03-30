package cz.cvut.fel.constructa.service;

import cz.cvut.fel.constructa.dto.request.CompanyRequest;
import cz.cvut.fel.constructa.mapper.CompanyMapper;
import cz.cvut.fel.constructa.model.Company;
import cz.cvut.fel.constructa.model.Location;
import cz.cvut.fel.constructa.repository.CompanyRepository;
import cz.cvut.fel.constructa.repository.LocationRepository;
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
    private final LocationRepository locationDao;
    private final CompanyMapper companyMapper;

    @Override
    public Company create(CompanyRequest request) throws ParseException {
        Location address = Location.builder()
                .active(false)
                .city(request.getCity())
                .street(request.getStreet())
                .country(request.getCountry())
                .descriptiveNumber(request.getDescriptiveNumber())
                .postCode(request.getPostCode())
                .build();
        locationDao.save(address);
        Company company = companyMapper.convertToEntity(request);
        company.setCompanyAddress(address);
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

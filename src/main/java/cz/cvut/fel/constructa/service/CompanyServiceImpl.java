package cz.cvut.fel.constructa.service;

import cz.cvut.fel.constructa.dto.request.CompanyRequest;
import cz.cvut.fel.constructa.dto.response.CompanyDTO;
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
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {
    private final CompanyRepository companyDao;
    private final LocationRepository locationDao;
    private final CompanyMapper companyMapper;

    @Override
    public CompanyDTO create(CompanyRequest request) throws ParseException {
        Location address = Location.builder()
                .city(request.getCity())
                .street(request.getStreet())
                .country(request.getCountry())
                .descriptiveNumber(request.getDescriptiveNumber())
                .postCode(request.getPostCode())
                .build();
        locationDao.save(address);
        Company company = companyMapper.convertToEntity(request);
        company.setCompanyAddress(address);
        Company createdCompany = companyDao.save(company);
        return companyMapper.convertToDto(createdCompany);
    }

    @Override
    public CompanyDTO getCompanyById(Long id) {
        Optional<Company> company = companyDao.findById(id);
        return company.map(companyMapper::convertToDto).orElse(null);

    }

    @Override
    public List<CompanyDTO> getCompanies() {
        List<Company> companies = companyDao.findAll();
        return companies.stream()
                .map(companyMapper::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        companyDao.deleteById(id);
    }

    @Override
    public CompanyDTO update(CompanyRequest request) throws ParseException {
        Optional<Company> company = companyDao.findById(request.getId());
        Location address = null;
        if (company.isPresent()) {
            address = company.get().getCompanyAddress();
            if (!Objects.equals(address.getCity(), request.getCity())) {
                address.setCity(request.getCity());
            }
            if (!Objects.equals(address.getStreet(), request.getStreet())) {
                address.setStreet(request.getStreet());
            }
            if (!Objects.equals(address.getDescriptiveNumber(), request.getDescriptiveNumber())) {
                address.setDescriptiveNumber(request.getDescriptiveNumber());
            }
            if (!Objects.equals(address.getPostCode(), request.getPostCode())) {
                address.setPostCode(request.getPostCode());
            }
            if (!Objects.equals(address.getCountry(), request.getCountry())) {
                address.setCountry(request.getCountry());
            }
            locationDao.save(address);
        }
        Company updatedCompany = companyMapper.convertToEntity(request);
        updatedCompany.setCompanyAddress(address);

        updatedCompany = companyDao.save(updatedCompany);

        return companyMapper.convertToDto(updatedCompany);
    }
}

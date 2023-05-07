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
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.data.domain.Sort;

/**
 * A service class that provides CRUD operations for companies and their locations.
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {
    /**
     * The data access object for companies.
     */
    private final CompanyRepository companyDao;
    /**
     * The data access object for locations.
     */
    private final LocationRepository locationDao;
    /**
     * The mapper for converting between Company entities and CompanyDTOs.
     */
    private final CompanyMapper companyMapper;

    /**
     * Creates a new company with the given details.
     *
     * @param request The details of the company to create.
     * @return The newly created company.
     * @throws ParseException if there is an error parsing the request data.
     */
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

        log.info("New company with id {} was added.", createdCompany.getId());

        return companyMapper.convertToDto(createdCompany);
    }

    /**
     * Retrieves a company by its ID.
     *
     * @param id The ID of the company to retrieve.
     * @return The company with the given ID, or null if no such company exists.
     */
    @Override
    public CompanyDTO getCompanyById(Long id) {
        Optional<Company> company = companyDao.findById(id);
        return company.map(companyMapper::convertToDto).orElse(null);

    }

    /**
     * Retrieves all companies.
     *
     * @return A list of all companies.
     */
    @Override
    public List<CompanyDTO> getCompanies() {
        Sort sortByName = Sort.by(Sort.Direction.ASC, "name");
        List<Company> companies = companyDao.findAll(sortByName);
        return companies.stream()
                .map(companyMapper::convertToDto)
                .collect(Collectors.toList());
    }

    /**
     * Deletes a company by its ID.
     *
     * @param id The ID of the company to delete.
     */
    @Override
    public void delete(Long id) {
        companyDao.deleteById(id);
        log.info("Company with id {} was deleted.", id);
    }

    /**
     * Updates an existing company with the given details.
     *
     * @param request The details of the company to update.
     * @return The updated company.
     * @throws ParseException if there is an error parsing the request data.
     */
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

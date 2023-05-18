package cz.cvut.fel.constructa.service;

import cz.cvut.fel.constructa.dto.request.CompanyRequest;
import cz.cvut.fel.constructa.dto.response.CompanyDTO;
import cz.cvut.fel.constructa.dto.response.LocationDTO;
import cz.cvut.fel.constructa.mapper.CompanyMapper;
import cz.cvut.fel.constructa.model.Company;
import cz.cvut.fel.constructa.model.Location;
import cz.cvut.fel.constructa.model.report.VehicleReport;
import cz.cvut.fel.constructa.model.role.User;
import cz.cvut.fel.constructa.repository.CompanyRepository;
import cz.cvut.fel.constructa.repository.LocationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Sort;

import java.text.ParseException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CompanyServiceImplTest {

    @Mock
    private CompanyRepository mockCompanyDao;
    @Mock
    private LocationRepository mockLocationDao;
    @Mock
    private CompanyMapper mockCompanyMapper;

    private CompanyServiceImpl companyServiceImplUnderTest;

    @BeforeEach
    void setUp() {
        companyServiceImplUnderTest = new CompanyServiceImpl(mockCompanyDao, mockLocationDao, mockCompanyMapper);
    }

    @Test
    void testCreate() throws Exception {
        // Setup
        final CompanyRequest request = CompanyRequest.builder()
                .id(0L)
                .country("country")
                .city("city")
                .street("street")
                .descriptiveNumber("descriptiveNumber")
                .postCode("postCode")
                .build();

        // Configure LocationRepository.save(...).
        final Location location = Location.builder()
                .city("city")
                .street("street")
                .descriptiveNumber("descriptiveNumber")
                .country("country")
                .postCode("postCode")
                .build();
        when(mockLocationDao.save(location)).thenReturn(location);

        // Configure CompanyMapper.convertToEntity(...).
        final Company company = new Company();
        company.setId(0L);
        company.setName("name");
        company.setDin("din");
        company.setCin("cin");
        company.setPhone("phone");
        company.setCompanyAddress(location);
        company.setExternalist(List.of(User.builder().build()));
        company.setContractorsTransportReports(Set.of(VehicleReport.builder().build()));
        when(mockCompanyMapper.convertToEntity(request)).thenReturn(company);

        // Configure CompanyRepository.save(...).
        final Company savedCompany = new Company();
        savedCompany.setId(0L);
        savedCompany.setName("name");
        savedCompany.setDin("din");
        savedCompany.setCin("cin");
        savedCompany.setPhone("phone");
        savedCompany.setCompanyAddress(location);
        savedCompany.setExternalist(List.of(User.builder().build()));
        savedCompany.setContractorsTransportReports(Set.of(VehicleReport.builder().build()));
        when(mockCompanyDao.save(company)).thenReturn(savedCompany);

        // Configure CompanyMapper.convertToDto(...).
        final CompanyDTO companyDTO = new CompanyDTO();
        companyDTO.setId(0L);
        companyDTO.setName("name");
        companyDTO.setDin("din");
        companyDTO.setCin("cin");
        companyDTO.setPhone("phone");
        final LocationDTO companyAddress = new LocationDTO();
        companyAddress.setId(0L);
        companyAddress.setCity("city");
        companyAddress.setStreet("street");
        companyAddress.setDescriptiveNumber("descriptiveNumber");
        companyAddress.setName("name");
        companyAddress.setCountry("country");
        companyAddress.setPostCode("postCode");
        companyDTO.setCompanyAddress(companyAddress);
        when(mockCompanyMapper.convertToDto(savedCompany)).thenReturn(companyDTO);

        // Run the test
        final CompanyDTO result = companyServiceImplUnderTest.create(request);

        // Verify the results
        verify(mockLocationDao).save(location);
    }

    @Test
    void testCreate_CompanyMapperConvertToEntityThrowsParseException() throws Exception {
        // Setup
        final CompanyRequest request = CompanyRequest.builder()
                .id(0L)
                .country("country")
                .city("city")
                .street("street")
                .descriptiveNumber("descriptiveNumber")
                .postCode("postCode")
                .build();

        // Configure LocationRepository.save(...).
        final Location location = Location.builder()
                .city("city")
                .street("street")
                .descriptiveNumber("descriptiveNumber")
                .country("country")
                .postCode("postCode")
                .build();
        when(mockLocationDao.save(Location.builder()
                .city("city")
                .street("street")
                .descriptiveNumber("descriptiveNumber")
                .country("country")
                .postCode("postCode")
                .build())).thenReturn(location);

        when(mockCompanyMapper.convertToEntity(CompanyRequest.builder()
                .id(0L)
                .country("country")
                .city("city")
                .street("street")
                .descriptiveNumber("descriptiveNumber")
                .postCode("postCode")
                .build())).thenThrow(ParseException.class);

        // Run the test
        assertThatThrownBy(() -> companyServiceImplUnderTest.create(request)).isInstanceOf(ParseException.class);
        verify(mockLocationDao).save(Location.builder()
                .city("city")
                .street("street")
                .descriptiveNumber("descriptiveNumber")
                .country("country")
                .postCode("postCode")
                .build());
    }

    @Test
    void testGetCompanyById() {
        // Setup
        // Configure CompanyRepository.findById(...).
        final Company company1 = new Company();
        company1.setId(0L);
        company1.setName("name");
        company1.setDin("din");
        company1.setCin("cin");
        company1.setPhone("phone");
        company1.setCompanyAddress(Location.builder()
                .city("city")
                .street("street")
                .descriptiveNumber("descriptiveNumber")
                .country("country")
                .postCode("postCode")
                .build());
        company1.setExternalist(List.of(User.builder().build()));
        company1.setContractorsTransportReports(Set.of(VehicleReport.builder().build()));
        final Optional<Company> company = Optional.of(company1);
        when(mockCompanyDao.findById(0L)).thenReturn(company);

        // Configure CompanyMapper.convertToDto(...).
        final CompanyDTO companyDTO = new CompanyDTO();
        companyDTO.setId(0L);
        companyDTO.setName("name");
        companyDTO.setDin("din");
        companyDTO.setCin("cin");
        companyDTO.setPhone("phone");
        final LocationDTO companyAddress = new LocationDTO();
        companyAddress.setId(0L);
        companyAddress.setCity("city");
        companyAddress.setStreet("street");
        companyAddress.setDescriptiveNumber("descriptiveNumber");
        companyAddress.setName("name");
        companyAddress.setCountry("country");
        companyAddress.setPostCode("postCode");
        companyDTO.setCompanyAddress(companyAddress);
        when(mockCompanyMapper.convertToDto(company1)).thenReturn(companyDTO);

        // Run the test
        final CompanyDTO result = companyServiceImplUnderTest.getCompanyById(0L);

        // Verify the results
        verify(mockCompanyMapper).convertToDto(company1);
    }


    @Test
    void testGetCompanyById_CompanyRepositoryReturnsAbsent() {
        // Setup
        when(mockCompanyDao.findById(0L)).thenReturn(Optional.empty());

        // Run the test
        final CompanyDTO result = companyServiceImplUnderTest.getCompanyById(0L);

        // Verify the results
        assertThat(result).isNull();
    }

    @Test
    void testGetCompanies() {
        // Setup
        // Configure CompanyRepository.findAll(...).
        final Company company = new Company();
        company.setId(0L);
        company.setName("name");
        company.setDin("din");
        company.setCin("cin");
        company.setPhone("phone");
        company.setCompanyAddress(Location.builder()
                .city("city")
                .street("street")
                .descriptiveNumber("descriptiveNumber")
                .country("country")
                .postCode("postCode")
                .build());
        company.setExternalist(List.of(User.builder().build()));
        company.setContractorsTransportReports(Set.of(VehicleReport.builder().build()));
        final List<Company> companies = List.of(company);
        when(mockCompanyDao.findAll(Sort.by("name"))).thenReturn(companies);

        // Configure CompanyMapper.convertToDto(...).
        final CompanyDTO companyDTO = new CompanyDTO();
        companyDTO.setId(0L);
        companyDTO.setName("name");
        companyDTO.setDin("din");
        companyDTO.setCin("cin");
        companyDTO.setPhone("phone");
        final LocationDTO companyAddress = new LocationDTO();
        companyAddress.setId(0L);
        companyAddress.setCity("city");
        companyAddress.setStreet("street");
        companyAddress.setDescriptiveNumber("descriptiveNumber");
        companyAddress.setName("name");
        companyAddress.setCountry("country");
        companyAddress.setPostCode("postCode");
        companyDTO.setCompanyAddress(companyAddress);
        when(mockCompanyMapper.convertToDto(company)).thenReturn(companyDTO);

        // Run the test
        final List<CompanyDTO> result = companyServiceImplUnderTest.getCompanies();

        // Verify the results
        verify(mockCompanyMapper).convertToDto(company);
    }

    @Test
    void testGetCompanies_CompanyRepositoryReturnsNoItems() {
        // Setup
        when(mockCompanyDao.findAll(Sort.by("name"))).thenReturn(Collections.emptyList());

        // Run the test
        final List<CompanyDTO> result = companyServiceImplUnderTest.getCompanies();

        // Verify the results
        assertThat(result).isEqualTo(Collections.emptyList());
    }


    @Test
    void testDelete() {
        // Setup
        // Run the test
        companyServiceImplUnderTest.delete(0L);

        // Verify the results
        verify(mockCompanyDao).deleteById(0L);
    }

    @Test
    void testUpdate() throws Exception {
        // Setup
        final CompanyRequest request = CompanyRequest.builder()
                .id(0L)
                .country("country")
                .city("city")
                .street("street")
                .descriptiveNumber("descriptiveNumber")
                .postCode("postCode")
                .build();

        // Configure CompanyRepository.findById(...).
        final Company company1 = new Company();
        company1.setId(0L);
        company1.setName("name");
        company1.setDin("din");
        company1.setCin("cin");
        company1.setPhone("phone");
        company1.setCompanyAddress(Location.builder()
                .city("city")
                .street("street")
                .descriptiveNumber("descriptiveNumber")
                .country("country")
                .postCode("postCode")
                .build());
        company1.setExternalist(List.of(User.builder().build()));
        company1.setContractorsTransportReports(Set.of(VehicleReport.builder().build()));
        final Optional<Company> company = Optional.of(company1);
        when(mockCompanyDao.findById(0L)).thenReturn(company);

        // Configure LocationRepository.save(...).
        final Location location = Location.builder()
                .city("city")
                .street("street")
                .descriptiveNumber("descriptiveNumber")
                .country("country")
                .postCode("postCode")
                .build();
        when(mockLocationDao.save(location)).thenReturn(location);

        // Configure CompanyMapper.convertToEntity(...).
        final Company updatedCompany = new Company();
        updatedCompany.setId(0L);
        updatedCompany.setName("name");
        updatedCompany.setDin("din");
        updatedCompany.setCin("cin");
        updatedCompany.setPhone("phone");
        updatedCompany.setCompanyAddress(location);
        updatedCompany.setExternalist(List.of(User.builder().build()));
        updatedCompany.setContractorsTransportReports(Set.of(VehicleReport.builder().build()));
        when(mockCompanyMapper.convertToEntity(request)).thenReturn(updatedCompany);

        // Configure CompanyRepository.save(...).
        when(mockCompanyDao.save(updatedCompany)).thenReturn(updatedCompany);

        // Configure CompanyMapper.convertToDto(...).
        final CompanyDTO updatedCompanyDTO = new CompanyDTO();
        updatedCompanyDTO.setId(0L);
        updatedCompanyDTO.setName("name");
        updatedCompanyDTO.setDin("din");
        updatedCompanyDTO.setCin("cin");
        updatedCompanyDTO.setPhone("phone");
        final LocationDTO updatedCompanyAddress = new LocationDTO();
        updatedCompanyAddress.setId(0L);
        updatedCompanyAddress.setCity("city");
        updatedCompanyAddress.setStreet("street");
        updatedCompanyAddress.setDescriptiveNumber("descriptiveNumber");
        updatedCompanyAddress.setName("name");
        updatedCompanyAddress.setCountry("country");
        updatedCompanyAddress.setPostCode("postCode");
        updatedCompanyDTO.setCompanyAddress(updatedCompanyAddress);
        when(mockCompanyMapper.convertToDto(updatedCompany)).thenReturn(updatedCompanyDTO);

        // Run the test
        final CompanyDTO result = companyServiceImplUnderTest.update(request);

        // Verify the results
        verify(mockLocationDao).save(location);
    }

    @Test
    void testUpdate_CompanyRepositoryFindByIdReturnsAbsent() throws Exception {
        // Setup
        final CompanyRequest request = CompanyRequest.builder()
                .id(0L)
                .country("country")
                .city("city")
                .street("street")
                .descriptiveNumber("descriptiveNumber")
                .postCode("postCode")
                .build();
        when(mockCompanyDao.findById(0L)).thenReturn(Optional.empty());

        // Configure CompanyMapper.convertToEntity(...).
        final Company updatedCompany = new Company();
        updatedCompany.setId(0L);
        updatedCompany.setName("name");
        updatedCompany.setDin("din");
        updatedCompany.setCin("cin");
        updatedCompany.setPhone("phone");
        updatedCompany.setCompanyAddress(Location.builder()
                .city("city")
                .street("street")
                .descriptiveNumber("descriptiveNumber")
                .country("country")
                .postCode("postCode")
                .build());
        updatedCompany.setExternalist(List.of(User.builder().build()));
        updatedCompany.setContractorsTransportReports(Set.of(VehicleReport.builder().build()));
        when(mockCompanyMapper.convertToEntity(request)).thenReturn(updatedCompany);

        // Configure CompanyRepository.save(...).
        when(mockCompanyDao.save(updatedCompany)).thenReturn(updatedCompany);

        // Configure CompanyMapper.convertToDto(...).
        final CompanyDTO updatedCompanyDTO = new CompanyDTO();
        updatedCompanyDTO.setId(0L);
        updatedCompanyDTO.setName("name");
        updatedCompanyDTO.setDin("din");
        updatedCompanyDTO.setCin("cin");
        updatedCompanyDTO.setPhone("phone");
        final LocationDTO updatedCompanyAddress = new LocationDTO();
        updatedCompanyAddress.setId(0L);
        updatedCompanyAddress.setCity("city");
        updatedCompanyAddress.setStreet("street");
        updatedCompanyAddress.setDescriptiveNumber("descriptiveNumber");
        updatedCompanyAddress.setName("name");
        updatedCompanyAddress.setCountry("country");
        updatedCompanyAddress.setPostCode("postCode");
        updatedCompanyDTO.setCompanyAddress(updatedCompanyAddress);
        when(mockCompanyMapper.convertToDto(updatedCompany)).thenReturn(updatedCompanyDTO);

        // Run the test
        final CompanyDTO result = companyServiceImplUnderTest.update(request);

        // Verify the results
        verify(mockCompanyDao).save(updatedCompany);
    }

    @Test
    void testUpdate_CompanyMapperConvertToEntityThrowsParseException() throws Exception {
        // Setup
        final CompanyRequest request = CompanyRequest.builder()
                .id(0L)
                .country("country")
                .city("city")
                .street("street")
                .descriptiveNumber("descriptiveNumber")
                .postCode("postCode")
                .build();

        // Configure CompanyRepository.findById(...).
        final Company company1 = new Company();
        company1.setId(0L);
        company1.setName("name");
        company1.setDin("din");
        company1.setCin("cin");
        company1.setPhone("phone");
        company1.setCompanyAddress(Location.builder()
                .city("city")
                .street("street")
                .descriptiveNumber("descriptiveNumber")
                .country("country")
                .postCode("postCode")
                .build());
        company1.setExternalist(List.of(User.builder().build()));
        company1.setContractorsTransportReports(Set.of(VehicleReport.builder().build()));
        final Optional<Company> company = Optional.of(company1);
        when(mockCompanyDao.findById(0L)).thenReturn(company);

        // Configure LocationRepository.save(...).
        final Location location = Location.builder()
                .city("city")
                .street("street")
                .descriptiveNumber("descriptiveNumber")
                .country("country")
                .postCode("postCode")
                .build();
        when(mockLocationDao.save(Location.builder()
                .city("city")
                .street("street")
                .descriptiveNumber("descriptiveNumber")
                .country("country")
                .postCode("postCode")
                .build())).thenReturn(location);

        when(mockCompanyMapper.convertToEntity(CompanyRequest.builder()
                .id(0L)
                .country("country")
                .city("city")
                .street("street")
                .descriptiveNumber("descriptiveNumber")
                .postCode("postCode")
                .build())).thenThrow(ParseException.class);

        // Run the test
        assertThatThrownBy(() -> companyServiceImplUnderTest.update(request)).isInstanceOf(ParseException.class);
        verify(mockLocationDao).save(Location.builder()
                .city("city")
                .street("street")
                .descriptiveNumber("descriptiveNumber")
                .country("country")
                .postCode("postCode")
                .build());
    }
}

package cz.cvut.fel.constructa.service.squaretest.util;

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
import cz.cvut.fel.constructa.service.CompanyServiceImpl;
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
        when(mockLocationDao.save(Location.builder()
                .city("city")
                .street("street")
                .descriptiveNumber("descriptiveNumber")
                .country("country")
                .postCode("postCode")
                .build())).thenReturn(location);

        // Configure CompanyMapper.convertToEntity(...).
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
        company.setContractors_transport_reports(Set.of(VehicleReport.builder().build()));
        when(mockCompanyMapper.convertToEntity(CompanyRequest.builder()
                .id(0L)
                .country("country")
                .city("city")
                .street("street")
                .descriptiveNumber("descriptiveNumber")
                .postCode("postCode")
                .build())).thenReturn(company);

        // Configure CompanyRepository.save(...).
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
        company1.setContractors_transport_reports(Set.of(VehicleReport.builder().build()));
        when(mockCompanyDao.save(new Company())).thenReturn(company1);

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
        when(mockCompanyMapper.convertToDto(new Company())).thenReturn(companyDTO);

        // Run the test
        final CompanyDTO result = companyServiceImplUnderTest.create(request);

        // Verify the results
        verify(mockLocationDao).save(Location.builder()
                .city("city")
                .street("street")
                .descriptiveNumber("descriptiveNumber")
                .country("country")
                .postCode("postCode")
                .build());
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
        company1.setContractors_transport_reports(Set.of(VehicleReport.builder().build()));
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
        when(mockCompanyMapper.convertToDto(new Company())).thenReturn(companyDTO);

        // Run the test
        final CompanyDTO result = companyServiceImplUnderTest.getCompanyById(0L);

        // Verify the results
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
        company.setContractors_transport_reports(Set.of(VehicleReport.builder().build()));
        final List<Company> companies = List.of(company);
        when(mockCompanyDao.findAll(Sort.by("properties"))).thenReturn(companies);

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
        when(mockCompanyMapper.convertToDto(new Company())).thenReturn(companyDTO);

        // Run the test
        final List<CompanyDTO> result = companyServiceImplUnderTest.getCompanies();

        // Verify the results
    }

    @Test
    void testGetCompanies_CompanyRepositoryReturnsNoItems() {
        // Setup
        when(mockCompanyDao.findAll(Sort.by("properties"))).thenReturn(Collections.emptyList());

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
        company1.setContractors_transport_reports(Set.of(VehicleReport.builder().build()));
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

        // Configure CompanyMapper.convertToEntity(...).
        final Company company2 = new Company();
        company2.setId(0L);
        company2.setName("name");
        company2.setDin("din");
        company2.setCin("cin");
        company2.setPhone("phone");
        company2.setCompanyAddress(Location.builder()
                .city("city")
                .street("street")
                .descriptiveNumber("descriptiveNumber")
                .country("country")
                .postCode("postCode")
                .build());
        company2.setExternalist(List.of(User.builder().build()));
        company2.setContractors_transport_reports(Set.of(VehicleReport.builder().build()));
        when(mockCompanyMapper.convertToEntity(CompanyRequest.builder()
                .id(0L)
                .country("country")
                .city("city")
                .street("street")
                .descriptiveNumber("descriptiveNumber")
                .postCode("postCode")
                .build())).thenReturn(company2);

        // Configure CompanyRepository.save(...).
        final Company company3 = new Company();
        company3.setId(0L);
        company3.setName("name");
        company3.setDin("din");
        company3.setCin("cin");
        company3.setPhone("phone");
        company3.setCompanyAddress(Location.builder()
                .city("city")
                .street("street")
                .descriptiveNumber("descriptiveNumber")
                .country("country")
                .postCode("postCode")
                .build());
        company3.setExternalist(List.of(User.builder().build()));
        company3.setContractors_transport_reports(Set.of(VehicleReport.builder().build()));
        when(mockCompanyDao.save(new Company())).thenReturn(company3);

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
        when(mockCompanyMapper.convertToDto(new Company())).thenReturn(companyDTO);

        // Run the test
        final CompanyDTO result = companyServiceImplUnderTest.update(request);

        // Verify the results
        verify(mockLocationDao).save(Location.builder()
                .city("city")
                .street("street")
                .descriptiveNumber("descriptiveNumber")
                .country("country")
                .postCode("postCode")
                .build());
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
        company.setContractors_transport_reports(Set.of(VehicleReport.builder().build()));
        when(mockCompanyMapper.convertToEntity(CompanyRequest.builder()
                .id(0L)
                .country("country")
                .city("city")
                .street("street")
                .descriptiveNumber("descriptiveNumber")
                .postCode("postCode")
                .build())).thenReturn(company);

        // Configure CompanyRepository.save(...).
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
        company1.setContractors_transport_reports(Set.of(VehicleReport.builder().build()));
        when(mockCompanyDao.save(new Company())).thenReturn(company1);

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
        when(mockCompanyMapper.convertToDto(new Company())).thenReturn(companyDTO);

        // Run the test
        final CompanyDTO result = companyServiceImplUnderTest.update(request);

        // Verify the results
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
        company1.setContractors_transport_reports(Set.of(VehicleReport.builder().build()));
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

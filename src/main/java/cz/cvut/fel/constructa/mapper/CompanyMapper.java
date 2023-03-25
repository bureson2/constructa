package cz.cvut.fel.constructa.mapper;

import cz.cvut.fel.constructa.dto.response.CompanyDTO;
import cz.cvut.fel.constructa.model.Company;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CompanyMapper {
    private final ModelMapper modelMapper;
    public CompanyDTO convertToDto(Company company) {
        return modelMapper.map(company, CompanyDTO.class);
    }
}

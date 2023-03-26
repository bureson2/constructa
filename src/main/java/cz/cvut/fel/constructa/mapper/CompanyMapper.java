package cz.cvut.fel.constructa.mapper;

import cz.cvut.fel.constructa.dto.request.CompanyRequest;
import cz.cvut.fel.constructa.dto.request.ProjectRequest;
import cz.cvut.fel.constructa.dto.response.CompanyDTO;
import cz.cvut.fel.constructa.model.Company;
import cz.cvut.fel.constructa.model.Project;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.text.ParseException;

@Component
@RequiredArgsConstructor
public class CompanyMapper {
    private final ModelMapper modelMapper;
    public CompanyDTO convertToDto(Company company) {
        return modelMapper.map(company, CompanyDTO.class);
    }

    public Company convertToEntity(CompanyRequest request) throws ParseException {
        return modelMapper.map(request, Company.class);
    }
}

package cz.cvut.fel.constructa.mapper;

import cz.cvut.fel.constructa.dto.request.CompanyRequest;
import cz.cvut.fel.constructa.dto.response.CompanyDTO;
import cz.cvut.fel.constructa.model.Company;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.text.ParseException;

/**
 * The type Company mapper.
 */
@Component
@RequiredArgsConstructor
public class CompanyMapper {
    /**
     * The Model mapper.
     */
    private final ModelMapper modelMapper;

    /**
     * Convert to dto company dto.
     *
     * @param company the company
     * @return the company dto
     */
    public CompanyDTO convertToDto(Company company) {
        return modelMapper.map(company, CompanyDTO.class);
    }

    /**
     * Convert to entity company.
     *
     * @param request the request
     * @return the company
     * @throws ParseException the parse exception
     */
    public Company convertToEntity(CompanyRequest request) throws ParseException {
        return modelMapper.map(request, Company.class);
    }
}

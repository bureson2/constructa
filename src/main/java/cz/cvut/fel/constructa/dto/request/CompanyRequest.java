package cz.cvut.fel.constructa.dto.request;

import cz.cvut.fel.constructa.dto.response.LocationDTO;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CompanyRequest {
    private String name;
    private String din;
    private String cin;
    private LocationDTO companyAddress;
}
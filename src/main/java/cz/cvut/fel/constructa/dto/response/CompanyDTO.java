package cz.cvut.fel.constructa.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CompanyDTO {
    private Long id;
    private String name;
    private String din;
    private String cin;
    private String phone;
    private LocationDTO companyAddress;
}

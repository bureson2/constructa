package cz.cvut.fel.constructa.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LocationDTO {
    private Long id;
    private String city;
    private String street;
    private String descriptiveNumber;
    private String name;
    private String country;
    private String postCode;
}

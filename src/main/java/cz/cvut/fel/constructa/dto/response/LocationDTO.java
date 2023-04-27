package cz.cvut.fel.constructa.dto.response;

import lombok.Getter;
import lombok.Setter;

/**
 * The type Location dto.
 */
@Getter
@Setter
public class LocationDTO {
    /**
     * The Id.
     */
    private Long id;
    /**
     * The City.
     */
    private String city;
    /**
     * The Street.
     */
    private String street;
    /**
     * The Descriptive number.
     */
    private String descriptiveNumber;
    /**
     * The Name.
     */
    private String name;
    /**
     * The Country.
     */
    private String country;
    /**
     * The Post code.
     */
    private String postCode;
}

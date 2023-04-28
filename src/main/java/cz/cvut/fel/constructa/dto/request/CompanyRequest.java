package cz.cvut.fel.constructa.dto.request;

import cz.cvut.fel.constructa.dto.response.LocationDTO;
import lombok.Builder;
import lombok.Data;

/**
 * The type Company request.
 */
@Data
@Builder
public class CompanyRequest {
    /**
     * The ID.
     */
    private Long id;
    /**
     * The Name.
     */
    private String name;
    /**
     * The Din.
     */
    private String din;
    /**
     * The Cin.
     */
    private String cin;
    /**
     * The Phone.
     */
    private String phone;
    /**
     * The Country.
     */
    private String country;
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
     * The Post code.
     */
    private String postCode;
}

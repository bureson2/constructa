package cz.cvut.fel.constructa.dto.response;

import lombok.Getter;
import lombok.Setter;

/**
 * The type Company dto.
 */
@Getter
@Setter
public class CompanyDTO {
    /**
     * The Id.
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
     * The Company address.
     */
    private LocationDTO companyAddress;
}

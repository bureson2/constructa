package cz.cvut.fel.constructa.dto.request;

import lombok.Data;

import java.util.Date;

/**
 * The type Illness request.
 */
@Data
public class IllnessRequest {
    /**
     * The Time from.
     */
    Date timeFrom;
    /**
     * The Time to.
     */
    Date timeTo;
}

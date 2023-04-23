package cz.cvut.fel.constructa.dto.request;

import lombok.Data;

import java.util.Date;

@Data
public class IllnessRequest {
    Date timeFrom;
    Date timeTo;
}

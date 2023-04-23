package cz.cvut.fel.constructa.dto.request;

import jakarta.persistence.Column;
import lombok.Data;

import java.util.Date;

@Data
public class AttendanceRequest {
    private Long locationId;
    private Double longitude;
    private Double latitude;
}

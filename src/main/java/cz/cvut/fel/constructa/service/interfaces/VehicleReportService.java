package cz.cvut.fel.constructa.service.interfaces;

import cz.cvut.fel.constructa.dto.request.VehicleReportRequest;
import cz.cvut.fel.constructa.dto.response.VehicleReportDTO;

import java.text.ParseException;
import java.util.List;

public interface VehicleReportService {
    VehicleReportDTO create(VehicleReportRequest request) throws ParseException;
    VehicleReportDTO getVehicleReportById(Long id);
    List<VehicleReportDTO> getVehicleReports();
    void deleteVehicleReport(Long id);
    VehicleReportDTO update(VehicleReportRequest request) throws ParseException;
    List<VehicleReportDTO> getVehicleReportsByVehicleId(Long id);
}

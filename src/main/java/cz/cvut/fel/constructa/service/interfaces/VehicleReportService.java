package cz.cvut.fel.constructa.service.interfaces;

import cz.cvut.fel.constructa.dto.request.VehicleReportRequest;
import cz.cvut.fel.constructa.model.report.VehicleReport;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;

public interface VehicleReportService {
    VehicleReport create(VehicleReportRequest request) throws ParseException;
    Optional<VehicleReport> getVehicleReportById(Long id);
    List<VehicleReport> getVehicleReports();
    void deleteVehicleReport(Long id);
    VehicleReport update(VehicleReport updatedVehicleReport);
    List<VehicleReport> getVehicleReportsByVehicleId(Long id);
}

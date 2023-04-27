package cz.cvut.fel.constructa.service.interfaces;

import cz.cvut.fel.constructa.dto.request.VehicleReportRequest;
import cz.cvut.fel.constructa.dto.response.VehicleReportDTO;

import java.text.ParseException;
import java.util.List;

/**
 * The interface Vehicle report service.
 */
public interface VehicleReportService {
    /**
     * Create vehicle report dto.
     *
     * @param request the request
     * @return the vehicle report dto
     * @throws ParseException the parse exception
     */
    VehicleReportDTO create(VehicleReportRequest request) throws ParseException;

    /**
     * Gets vehicle report by id.
     *
     * @param id the id
     * @return the vehicle report by id
     */
    VehicleReportDTO getVehicleReportById(Long id);

    /**
     * Gets vehicle reports.
     *
     * @return the vehicle reports
     */
    List<VehicleReportDTO> getVehicleReports();

    /**
     * Delete vehicle report.
     *
     * @param id the id
     */
    void deleteVehicleReport(Long id);

    /**
     * Update vehicle report dto.
     *
     * @param request the request
     * @return the vehicle report dto
     * @throws ParseException the parse exception
     */
    VehicleReportDTO update(VehicleReportRequest request) throws ParseException;

    /**
     * Gets vehicle reports by vehicle id.
     *
     * @param id the id
     * @return the vehicle reports by vehicle id
     */
    List<VehicleReportDTO> getVehicleReportsByVehicleId(Long id);
}

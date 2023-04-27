package cz.cvut.fel.constructa.service.interfaces;

import cz.cvut.fel.constructa.dto.request.AttendanceRequest;
import cz.cvut.fel.constructa.dto.request.IllnessRequest;
import cz.cvut.fel.constructa.dto.request.StopAttendanceRequest;
import cz.cvut.fel.constructa.dto.request.WorkReportRequest;
import cz.cvut.fel.constructa.dto.response.LocationDTO;
import cz.cvut.fel.constructa.dto.response.WorkReportDTO;

import java.text.ParseException;
import java.util.List;

/**
 * The interface Work report service.
 */
public interface WorkReportService {

    /**
     * Create work report dto.
     *
     * @param request the request
     * @return the work report dto
     * @throws ParseException the parse exception
     */
    WorkReportDTO create(WorkReportRequest request) throws ParseException;

    /**
     * Record illness.
     *
     * @param request the request
     */
    void recordIllness(IllnessRequest request);

    /**
     * Record work report work report dto.
     *
     * @param request the request
     * @return the work report dto
     */
    WorkReportDTO recordWorkReport(AttendanceRequest request);

    /**
     * Stop work report record.
     *
     * @param request the request
     */
    void stopWorkReportRecord(StopAttendanceRequest request);

    /**
     * Gets work report by id.
     *
     * @param id the id
     * @return the work report by id
     */
    WorkReportDTO getWorkReportById(Long id);

    /**
     * Gets work reports.
     *
     * @return the work reports
     */
    List<WorkReportDTO> getWorkReports();

    /**
     * Gets my work reports.
     *
     * @return the my work reports
     */
    List<WorkReportDTO> getMyWorkReports();

    /**
     * Gets work reports by reporting employee id.
     *
     * @param id the id
     * @return the work reports by reporting employee id
     */
    List<WorkReportDTO> getWorkReportsByReportingEmployeeId(Long id);

    /**
     * Gets worklocation by id.
     *
     * @param id the id
     * @return the worklocation by id
     */
    LocationDTO getWorklocationById(Long id);

    /**
     * Delete.
     *
     * @param id the id
     */
    void delete(Long id);

    /**
     * Update work report dto.
     *
     * @param request the request
     * @return the work report dto
     * @throws ParseException the parse exception
     */
    WorkReportDTO update(WorkReportRequest request) throws ParseException;
}

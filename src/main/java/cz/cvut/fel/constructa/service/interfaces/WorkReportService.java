package cz.cvut.fel.constructa.service.interfaces;

import cz.cvut.fel.constructa.dto.request.AttendanceRequest;
import cz.cvut.fel.constructa.dto.request.IllnessRequest;
import cz.cvut.fel.constructa.dto.request.WorkReportRequest;
import cz.cvut.fel.constructa.dto.response.LocationDTO;
import cz.cvut.fel.constructa.dto.response.WorkReportDTO;

import java.text.ParseException;
import java.util.List;

public interface WorkReportService {

    WorkReportDTO create(WorkReportRequest request) throws ParseException;
    void recordIllness(IllnessRequest request);
    WorkReportDTO recordWorkReport(AttendanceRequest request);
    WorkReportDTO getWorkReportById(Long id);
    List<WorkReportDTO> getWorkReports();
    List<WorkReportDTO> getMyWorkReports();
    List<WorkReportDTO> getWorkReportsByReportingEmployeeId(Long id);
    LocationDTO getWorklocationById(Long id);
    void delete(Long id);
    WorkReportDTO update(WorkReportRequest request) throws ParseException;
}

package cz.cvut.fel.constructa.service.interfaces;

import cz.cvut.fel.constructa.dto.request.WorkReportRequest;
import cz.cvut.fel.constructa.dto.response.WorkReportDTO;

import java.text.ParseException;
import java.util.List;

public interface WorkReportService {

    WorkReportDTO create(WorkReportRequest request) throws ParseException;
    WorkReportDTO getWorkReportById(Long id);
    List<WorkReportDTO> getWorkReports();
    List<WorkReportDTO> getMyWorkReports();
    List<WorkReportDTO> getWorkReportsByReportingEmployeeId(Long id);
    void delete(Long id);
    WorkReportDTO update(WorkReportRequest request) throws ParseException;
}

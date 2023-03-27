package cz.cvut.fel.constructa.service.interfaces;

import cz.cvut.fel.constructa.dto.request.TaskRequest;
import cz.cvut.fel.constructa.dto.request.WorkReportRequest;
import cz.cvut.fel.constructa.dto.response.WorkReportDTO;
import cz.cvut.fel.constructa.model.Task;
import cz.cvut.fel.constructa.model.report.WorkReport;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;

public interface WorkReportService {

    WorkReport create(WorkReportRequest request) throws ParseException;
    Optional<WorkReport> getWorkReportById(Long id);
    List<WorkReportDTO> getWorkReports();
    List<WorkReportDTO> getWorkReportsByReportingEmployeeId(Long id);
    void delete(Long id);
    WorkReport update(WorkReport updatedWorkReport);
}

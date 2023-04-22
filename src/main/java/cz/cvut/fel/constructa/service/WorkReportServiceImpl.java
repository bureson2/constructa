package cz.cvut.fel.constructa.service;

import cz.cvut.fel.constructa.dto.request.WorkReportRequest;
import cz.cvut.fel.constructa.dto.response.WorkReportDTO;
import cz.cvut.fel.constructa.mapper.WorkReportMapper;
import cz.cvut.fel.constructa.model.Location;
import cz.cvut.fel.constructa.model.Task;
import cz.cvut.fel.constructa.model.report.WorkReport;
import cz.cvut.fel.constructa.model.role.User;
import cz.cvut.fel.constructa.repository.UserRepository;
import cz.cvut.fel.constructa.repository.WorkReportRepository;
import cz.cvut.fel.constructa.security.AuthenticationFacade;
import cz.cvut.fel.constructa.service.interfaces.WorkReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WorkReportServiceImpl implements WorkReportService {
    private final UserRepository userDao;
    private final WorkReportRepository workReportDao;
    private final WorkReportMapper workReportMapper;
    private final AuthenticationFacade authenticationFacade;

    @Override
    public WorkReportDTO create(WorkReportRequest request) throws ParseException {
        WorkReport createdWorkReport = workReportMapper.convertToEntity(request);
        Optional<User> user = userDao.findById(request.getUserId());
        user.ifPresent(createdWorkReport::setReportingEmployee);
        createdWorkReport = workReportDao.save(createdWorkReport);
        return workReportMapper.convertToDto(createdWorkReport);
    }

    @Override
    public WorkReportDTO getWorkReportById(Long id) {
        Optional<WorkReport> workReport = workReportDao.findAll().stream().filter(it -> it.getId().equals(id)).findFirst();
        return workReport.map(workReportMapper::convertToDto).orElse(null);
    }

    @Override
    public List<WorkReportDTO> getWorkReports() {
        List<WorkReport> workReports = workReportDao.findAll();
        return workReports.stream()
                        .map(workReportMapper::convertToDto)
                        .collect(Collectors.toList());
    }

    @Override
    public List<WorkReportDTO> getMyWorkReports() {
        String authorEmail = authenticationFacade.getAuthentication().getName();
        Optional<User> author = userDao.findByEmail(authorEmail);
        List<WorkReport> reports = new ArrayList<>();

        if(author.isPresent()) {
            reports = workReportDao.findWorkReportsByReportingEmployeeId(author.get().getId());
        }

        return reports.stream()
                .map(workReportMapper::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<WorkReportDTO> getWorkReportsByReportingEmployeeId(Long id) {
        List<WorkReport> workReports = workReportDao.findWorkReportsByReportingEmployeeId(id);
        return workReports.stream()
                .map(workReportMapper::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        workReportDao.deleteById(id);
    }

    @Override
    public WorkReportDTO update(WorkReportRequest request) throws ParseException {
        Optional<WorkReport> report = workReportDao.findById(request.getId());
        User employee = null;
        Location location = null;

        if (report.isPresent()) {
            employee = report.get().getReportingEmployee();
            location = report.get().getLocation();
        }
        WorkReport updatedReport = workReportMapper.convertToEntity(request);
        updatedReport.setReportingEmployee(employee);
        updatedReport.setLocation(location);
        updatedReport = workReportDao.save(updatedReport);

        return workReportMapper.convertToDto(updatedReport);
    }
}

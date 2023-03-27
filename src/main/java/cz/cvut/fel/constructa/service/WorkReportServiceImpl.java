package cz.cvut.fel.constructa.service;

import cz.cvut.fel.constructa.dto.request.WorkReportRequest;
import cz.cvut.fel.constructa.dto.response.WorkReportDTO;
import cz.cvut.fel.constructa.mapper.WorkReportMapper;
import cz.cvut.fel.constructa.model.report.WorkReport;
import cz.cvut.fel.constructa.model.role.User;
import cz.cvut.fel.constructa.repository.UserRepository;
import cz.cvut.fel.constructa.repository.WorkReportRepository;
import cz.cvut.fel.constructa.service.interfaces.WorkReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WorkReportServiceImpl implements WorkReportService {
    private final UserRepository userDao;
    private final WorkReportRepository workReportDao;
    private final WorkReportMapper workReportMapper;
    @Override
    public WorkReport create(WorkReportRequest request) throws ParseException {
        WorkReport createdWorkReport = workReportMapper.convertToEntity(request);
        Optional<User> user = userDao.findById(request.getUserId());
        user.ifPresent(createdWorkReport::setReportingEmployee);
        return workReportDao.save(createdWorkReport);
    }

    @Override
    public Optional<WorkReport> getWorkReportById(Long id) {
        return workReportDao.findAll().stream().filter(it -> it.getId().equals(id)).findFirst();
//        return workReportDao.findById(id);
    }

    @Override
    public List<WorkReportDTO> getWorkReports() {
        List<WorkReport> workReports = workReportDao.findAll();
        return workReports.stream()
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
    public WorkReport update(WorkReport updatedWorkReport) {
//        TODO
        return null;
    }
}

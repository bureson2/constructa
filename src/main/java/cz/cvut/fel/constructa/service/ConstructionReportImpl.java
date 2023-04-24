package cz.cvut.fel.constructa.service;

import cz.cvut.fel.constructa.dto.request.ConstructionReportRequest;
import cz.cvut.fel.constructa.dto.response.ConstructionReportDTO;
import cz.cvut.fel.constructa.mapper.ConstructionReportMapper;
import cz.cvut.fel.constructa.model.Project;
import cz.cvut.fel.constructa.model.report.ConstructionReport;
import cz.cvut.fel.constructa.model.role.User;
import cz.cvut.fel.constructa.repository.ConstructionReportRepository;
import cz.cvut.fel.constructa.repository.ProjectRepository;
import cz.cvut.fel.constructa.repository.UserRepository;
import cz.cvut.fel.constructa.security.AuthenticationFacade;
import cz.cvut.fel.constructa.service.interfaces.ConstructionReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ConstructionReportImpl implements ConstructionReportService {
    private final ConstructionReportRepository constructionReportDao;
    private final UserRepository userDao;
    private final ProjectRepository projectDao;
    private final ConstructionReportMapper constructionReportMapper;
    private final AuthenticationFacade authenticationFacade;

    @Override
    public ConstructionReportDTO create(ConstructionReportRequest request) throws ParseException {

        ConstructionReport constructionReport = constructionReportMapper.convertToEntity(request);

        Optional<User> user = Optional.empty();
        if(request.getExecutorId() != null){
            user = userDao.findById(request.getExecutorId());
        }

        if(user.isPresent()){
            constructionReport.setExecutor(user.get());
        } else {
            String authorEmail = authenticationFacade.getAuthentication().getName();
            Optional<User> author = userDao.findByEmail(authorEmail);
            author.ifPresent(constructionReport::setExecutor);
        }

        Optional<Project> project = projectDao.findById(request.getProjectId());
        project.ifPresent(constructionReport::setProject);
        ConstructionReport createdReport = constructionReportDao.save(constructionReport);

        return constructionReportMapper.convertToDto(createdReport);
    }

    @Override
    public ConstructionReportDTO getConstructionReporttById(Long id) {
        Optional<ConstructionReport> constructionReport = constructionReportDao.findById(id);
        return constructionReport.map(constructionReportMapper::convertToDto).orElse(null);
    }

    @Override
    public List<ConstructionReportDTO> getConstructionReports() {
        List<ConstructionReport> constructionReports = constructionReportDao.findAll();
        return constructionReports.stream()
                .map(constructionReportMapper::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ConstructionReportDTO> getConstructionReportsByProjectId(Long projectId) {
        List<ConstructionReport> constructionReports = constructionReportDao.findAllByProjectId(projectId);
        return constructionReports.stream()
                .map(constructionReportMapper::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        constructionReportDao.deleteById(id);
    }

    @Override
    public ConstructionReport update(ConstructionReportRequest constructionReport) {
        return null;
    }
}

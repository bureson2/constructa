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

/**
 * Service class that handles construction reports.
 */
@Service
@RequiredArgsConstructor
public class ConstructionReportImpl implements ConstructionReportService {
    /**
     * Repository for construction reports.
     */
    private final ConstructionReportRepository constructionReportDao;
    /**
     * Repository for users.
     */
    private final UserRepository userDao;
    /**
     * Repository for projects.
     */
    private final ProjectRepository projectDao;
    /**
     * Mapper for construction reports.
     */
    private final ConstructionReportMapper constructionReportMapper;
    /**
     * Authentication facade for retrieving the current user.
     */
    private final AuthenticationFacade authenticationFacade;

    /**
     * Creates a new construction report based on the provided request.
     *
     * @param request the construction report request
     * @return the created construction report DTO
     * @throws ParseException if there was an error parsing a date in the request
     */
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

    /**
     * Retrieves the construction report with the specified ID.
     *
     * @param id the ID of the construction report to retrieve
     * @return the construction report DTO, or null if no such report exists
     */
    @Override
    public ConstructionReportDTO getConstructionReporttById(Long id) {
        Optional<ConstructionReport> constructionReport = constructionReportDao.findById(id);
        return constructionReport.map(constructionReportMapper::convertToDto).orElse(null);
    }

    /**
     * Retrieves all construction reports.
     *
     * @return a list of all construction report DTOs
     */
    @Override
    public List<ConstructionReportDTO> getConstructionReports() {
        List<ConstructionReport> constructionReports = constructionReportDao.findAll();
        return constructionReports.stream()
                .map(constructionReportMapper::convertToDto)
                .collect(Collectors.toList());
    }

    /**
     * Retrieves all construction reports for the project with the specified ID.
     *
     * @param projectId the ID of the project for which to retrieve reports
     * @return a list of all construction report DTOs for the specified project
     */
    @Override
    public List<ConstructionReportDTO> getConstructionReportsByProjectId(Long projectId) {
        List<ConstructionReport> constructionReports = constructionReportDao.findAllByProjectId(projectId);
        return constructionReports.stream()
                .map(constructionReportMapper::convertToDto)
                .collect(Collectors.toList());
    }

    /**
     * Deletes the construction report with the specified ID.
     *
     * @param id the ID of the construction report to delete
     */
    @Override
    public void delete(Long id) {
        constructionReportDao.deleteById(id);
    }

    /**
     * Updates the specified construction report.
     *
     * @param constructionReport the construction report to update
     * @return the updated construction report
     */
    @Override
    public ConstructionReport update(ConstructionReportRequest constructionReport) {
        return null;
    }
}

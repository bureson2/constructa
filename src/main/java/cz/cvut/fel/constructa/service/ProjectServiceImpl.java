package cz.cvut.fel.constructa.service;

import cz.cvut.fel.constructa.dto.request.ProjectRequest;
import cz.cvut.fel.constructa.dto.response.ProjectDTO;
import cz.cvut.fel.constructa.enums.ProjectState;
import cz.cvut.fel.constructa.mapper.ProjectMapper;
import cz.cvut.fel.constructa.model.Location;
import cz.cvut.fel.constructa.model.Project;
import cz.cvut.fel.constructa.model.role.User;
import cz.cvut.fel.constructa.repository.LocationRepository;
import cz.cvut.fel.constructa.repository.ProjectRepository;
import cz.cvut.fel.constructa.repository.UserRepository;
import cz.cvut.fel.constructa.service.interfaces.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.data.domain.Sort;

/**
 * The service for managing projects.
 */
@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {
    /**
     * The repository for project entities.
     */
    private final ProjectRepository projectDao;
    /**
     * The repository for user entities.
     */
    private final UserRepository userDao;
    /**
     * The repository for location entities.
     */
    private final LocationRepository locationDao;
    /**
     * The mapper for converting between ProjectRequest and Project entities.
     */
    private final ProjectMapper projectMapper;

    /**
     * Creates a new project with the given parameters.
     *
     * @param request the request containing the data for the new project
     * @return the created project DTO
     * @throws ParseException if there is an error parsing the date in the request
     */
    @Override
    public ProjectDTO create(ProjectRequest request) throws ParseException {
        Project createdProject = projectMapper.convertToEntity(request);
        Optional<User> user = userDao.findById(request.getUserId());
        user.ifPresent(createdProject::setProjectManager);
        createdProject.setState(ProjectState.IN_PREPARATION);

        Location address = Location.builder()
                .city(request.getCity())
                .street(request.getStreet())
                .country(request.getCountry())
                .descriptiveNumber(request.getDescriptiveNumber())
                .postCode(request.getPostCode())
                .build();
        locationDao.save(address);
        createdProject.setProjectAddress(address);
        createdProject = projectDao.save(createdProject);
        return projectMapper.convertToDto(createdProject);
    }

    /**
     * Gets the project with the given ID.
     *
     * @param id the ID of the project to retrieve
     * @return the project DTO with the given ID, or null if no project with that ID exists
     */
    @Override
    public ProjectDTO getProjectById(Long id) {
        Optional<Project> project = projectDao.findById(id);
        return project.map(projectMapper::convertToDto).orElse(null);
    }

    /**
     * Returns a list of all projects in the database, mapped to DTO objects.
     * Note that this method returns all projects, which could potentially be a large amount of data.
     * Consider using pagination or filtering to limit the amount of data returned.
     *
     * @return a list of all projects
     */
    @Override
    public List<ProjectDTO> getProjects() {
        Sort sortByName = Sort.by(Sort.Direction.ASC, "name");
        List<Project> projects = projectDao.findAll(sortByName);
        return projects.stream()
                .map(projectMapper::convertToDto)
                .collect(Collectors.toList());
    }

    /**
     * Updates an existing project.
     *
     * @param request the project request
     * @return the updated project DTO
     * @throws ParseException if there's a problem parsing the request
     */
    @Override
    public ProjectDTO update(ProjectRequest request) throws ParseException {
        Optional<Project> project = projectDao.findById(request.getId());

        Location address = null;

        if (project.isPresent()) {
            address = project.get().getProjectAddress();
            if (!Objects.equals(address.getCity(), request.getCity())) {
                address.setCity(request.getCity());
            }
            if (!Objects.equals(address.getStreet(), request.getStreet())) {
                address.setStreet(request.getStreet());
            }
            if (!Objects.equals(address.getDescriptiveNumber(), request.getDescriptiveNumber())) {
                address.setDescriptiveNumber(request.getDescriptiveNumber());
            }
            if (!Objects.equals(address.getPostCode(), request.getPostCode())) {
                address.setPostCode(request.getPostCode());
            }
            if (!Objects.equals(address.getCountry(), request.getCountry())) {
                address.setCountry(request.getCountry());
            }
            locationDao.save(address);
        }

        Project updatedProject = projectMapper.convertToEntity(request);
        updatedProject.setProjectAddress(address);

        Optional<User> user = userDao.findById(request.getUserId());
        user.ifPresent(updatedProject::setProjectManager);

        updatedProject = projectDao.save(updatedProject);

        return projectMapper.convertToDto(updatedProject);
    }


    /**
     * Deletes a project.
     *
     * @param id the project ID
     */
    @Override
    public void delete(Long id) {
        projectDao.deleteById(id);
    }
}

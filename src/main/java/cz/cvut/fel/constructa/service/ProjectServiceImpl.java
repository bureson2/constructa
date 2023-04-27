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

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {
    private final ProjectRepository projectDao;
    private final UserRepository userDao;
    private final LocationRepository locationDao;
    private final ProjectMapper projectMapper;

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

    @Override
    public ProjectDTO getProjectById(Long id) {
        Optional<Project> project = projectDao.findById(id);
        return project.map(projectMapper::convertToDto).orElse(null);
    }

    @Override
    public List<ProjectDTO> getProjects() {
        List<Project> projects = projectDao.findAll();
        return projects.stream()
                .map(projectMapper::convertToDto)
                .collect(Collectors.toList());
    }

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

    @Override
    public void delete(Long id) {
        projectDao.deleteById(id);
    }
}

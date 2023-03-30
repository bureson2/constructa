package cz.cvut.fel.constructa.service;

import cz.cvut.fel.constructa.dto.request.ProjectRequest;
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
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {
    private final ProjectRepository projectDao;
    private final UserRepository userDao;
    private final LocationRepository locationDao;
    private final ProjectMapper projectMapper;
    @Override
    public Project create(ProjectRequest request) throws ParseException {
        Project createdProject = projectMapper.convertToEntity(request);
        Optional<User> user = userDao.findById(request.getUserId());
        user.ifPresent(createdProject::setProjectManager);
        createdProject.setState(ProjectState.IN_PREPARATION);

        Location address = Location.builder()
                .active(false)
                .city(request.getCity())
                .street(request.getStreet())
                .country(request.getCountry())
                .descriptiveNumber(request.getDescriptiveNumber())
                .postCode(request.getPostCode())
                .build();
        locationDao.save(address);
        createdProject.setProjectAddress(address);

        return projectDao.save(createdProject);
    }

    @Override
    public Optional<Project> getProjectById(Long id) {
        return projectDao.findById(id);
    }

    @Override
    public List<Project> getProjects() {
        return projectDao.findAll();
    }

    @Override
    public void delete(Long id) {
        projectDao.deleteById(id);
    }

    @Override
    public Project update(Project updatedProject) {
        return projectDao.save(updatedProject);
    }
}

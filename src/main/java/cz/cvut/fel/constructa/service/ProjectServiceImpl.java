package cz.cvut.fel.constructa.service;

import cz.cvut.fel.constructa.model.Project;
import cz.cvut.fel.constructa.repository.ProjectRepository;
import cz.cvut.fel.constructa.service.interfaces.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {
    private final ProjectRepository projectDao;
    @Override
    public Project create(Project project) {
        return projectDao.save(project);
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

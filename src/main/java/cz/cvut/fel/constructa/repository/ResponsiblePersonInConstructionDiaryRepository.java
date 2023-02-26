package cz.cvut.fel.constructa.repository;

import cz.cvut.fel.constructa.model.role.ResponsiblePersonInConstructionDiary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResponsiblePersonInConstructionDiaryRepository extends JpaRepository<ResponsiblePersonInConstructionDiary, Long> {
}

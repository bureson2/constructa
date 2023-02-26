package cz.cvut.fel.constructa.repository;

import cz.cvut.fel.constructa.model.ConstructionDiary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConstructionDiaryRepository extends JpaRepository<ConstructionDiary, Long> {
}

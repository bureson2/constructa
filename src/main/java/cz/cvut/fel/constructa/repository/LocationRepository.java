package cz.cvut.fel.constructa.repository;

import cz.cvut.fel.constructa.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * The interface Location repository.
 */
@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {
}

package cz.cvut.fel.constructa.repository;

import cz.cvut.fel.constructa.enums.VehicleType;
import cz.cvut.fel.constructa.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * The interface Vehicle repository.
 */
@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
    /**
     * Find by type list.
     *
     * @param type the type
     * @return the list
     */
    List<Vehicle> findByType(VehicleType type);
}

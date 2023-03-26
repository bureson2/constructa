package cz.cvut.fel.constructa.repository;

import cz.cvut.fel.constructa.enums.VehicleType;
import cz.cvut.fel.constructa.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
    List<Vehicle> findByType(VehicleType type);
}

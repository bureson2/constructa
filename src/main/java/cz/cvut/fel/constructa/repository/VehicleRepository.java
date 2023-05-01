package cz.cvut.fel.constructa.repository;

import cz.cvut.fel.constructa.enums.VehicleType;
import cz.cvut.fel.constructa.model.Vehicle;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import org.springframework.data.domain.Sort;

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

    /**
     * Find all list.
     *
     * @param sort the sort
     * @return the list
     */
    @NotNull
    List<Vehicle> findAll(@NotNull Sort sort);

}

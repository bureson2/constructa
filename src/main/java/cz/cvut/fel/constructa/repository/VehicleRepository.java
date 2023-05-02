package cz.cvut.fel.constructa.repository;

import cz.cvut.fel.constructa.enums.VehicleType;
import cz.cvut.fel.constructa.model.Vehicle;
import jakarta.transaction.Transactional;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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

    /**
     * Sets driver to null by user id.
     *
     * @param userId the user id
     */
    @Transactional
    @Modifying
    @Query("UPDATE VehicleReport vr SET vr.driver = null WHERE vr.driver.id = :userId")
    void setDriverToNullByUserId(@Param("userId") Long userId);

}

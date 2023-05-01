package cz.cvut.fel.constructa.model.loan;

import cz.cvut.fel.constructa.model.Location;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Warehouse represents a storage facility for devices.
 */
@Entity
@Table(name = "warehouses")
@Data
public class Warehouse {
    /**
     * The ID of the warehouse.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "warehouse_id", nullable = false)
    private Long id;

    /**
     * The list of devices stored in the warehouse.
     */
    @OneToMany(mappedBy = "warehouse")
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private List<Device> devices = new ArrayList<>();

    /**
     * The address of the warehouse.
     */
    @OneToOne
    @JoinColumn(name = "location_id", referencedColumnName = "location_id")
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private Location warehouseAddress;
}

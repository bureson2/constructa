package cz.cvut.fel.constructa.model.loan;

import cz.cvut.fel.constructa.model.Location;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    private List<Device> devices = new ArrayList<>();

    /**
     * The address of the warehouse.
     */
    @OneToOne
    @JoinColumn(name = "location_id", referencedColumnName = "location_id")
    private Location warehouseAddress;
}

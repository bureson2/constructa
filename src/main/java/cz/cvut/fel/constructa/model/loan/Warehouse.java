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

@Entity
@Table(name = "warehouses")
@Data
public class Warehouse {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "warehouse_id", nullable = false)
    private Long id;

    @OneToMany(mappedBy = "warehouse")
    private List<Device> devices = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "location_id", referencedColumnName = "location_id")
    private Location warehouseAddress;
}

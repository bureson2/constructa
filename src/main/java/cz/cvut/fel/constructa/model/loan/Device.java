package cz.cvut.fel.constructa.model.loan;

import cz.cvut.fel.constructa.enums.DeviceState;
import cz.cvut.fel.constructa.enums.DeviceType;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.HashSet;
import java.util.Set;

/**
 * Represents a device that can be loaned from a warehouse.
 */
@Entity
@Table(name = "devices")
@Data
public class Device {
    /**
     * The ID of the device.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "device_id", nullable = false)
    private Long id;

    /**
     * The name of the device.
     */
    @Column(name="name", nullable = false)
    private String name;

    /**
     * The type of the device.
     */
    @Enumerated(EnumType.STRING)
    private DeviceType type;

    /**
     * The current state of the device.
     */
    @Enumerated(EnumType.STRING)
    private DeviceState state;

    /**
     * The warehouse where the device is located.
     */
    @ManyToOne
    @JoinColumn(name = "warehouse_id")
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private Warehouse warehouse;

    /**
     * The set of loans that this device has been a part of.
     */
    @ManyToMany(mappedBy = "loanedDevices")
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private Set<Loan> realizedLoans = new HashSet<>();
}

package cz.cvut.fel.constructa.model;

import cz.cvut.fel.constructa.enums.TaskState;
import cz.cvut.fel.constructa.enums.VehicleType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "vehicles")
@Getter
@Setter
@NoArgsConstructor
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "vehicle_id", nullable = false)
    private Long id;

    @Column(name="factory")
    private String factory;

    @Column(name="name")
    private String name;

    @Column(name="qr_code")
    private String qrCode;

    @Column(name="registration_number")
    private String registrationNumber;

    @Enumerated(EnumType.STRING)
    private VehicleType type;
}

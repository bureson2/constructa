package cz.cvut.fel.constructa.model.loan;

import cz.cvut.fel.constructa.enums.DeviceState;
import cz.cvut.fel.constructa.enums.DeviceType;
import cz.cvut.fel.constructa.enums.Role;
import cz.cvut.fel.constructa.model.role.ResponsiblePersonInConstructionDiary;
import cz.cvut.fel.constructa.model.role.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "devices")
@Getter
@Setter
@NoArgsConstructor
public class Device {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "device_id", nullable = false)
    private Long id;

    @Column(name="name")
    private String name;

    @Column(name="qr_code")
    private String qrCode;

    @Enumerated(EnumType.STRING)
    private DeviceType type;

    @Enumerated(EnumType.STRING)
    private DeviceState state;

    @ManyToOne
    @JoinColumn(name = "warehouse_id")
    private Warehouse warehouse;

    @ManyToMany(mappedBy = "loanedDevices")
    private Set<Loan> realizedLoans = new HashSet<>();
}

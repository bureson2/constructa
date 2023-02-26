package cz.cvut.fel.constructa.repository;

import cz.cvut.fel.constructa.model.loan.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeviceRepository extends JpaRepository<Device, Long> {
}

package cz.cvut.fel.constructa.repository;

import cz.cvut.fel.constructa.model.loan.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WarehouseRepository extends JpaRepository<Warehouse, Long> {
}

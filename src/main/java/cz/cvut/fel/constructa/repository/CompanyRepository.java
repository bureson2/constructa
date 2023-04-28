package cz.cvut.fel.constructa.repository;

import cz.cvut.fel.constructa.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * The interface Company repository.
 */
@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
}

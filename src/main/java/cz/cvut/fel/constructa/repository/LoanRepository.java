package cz.cvut.fel.constructa.repository;

import cz.cvut.fel.constructa.model.loan.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * The interface Loan repository.
 */
@Repository
public interface LoanRepository extends JpaRepository<Loan, Long> {
}

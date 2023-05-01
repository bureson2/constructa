package cz.cvut.fel.constructa.repository;

import cz.cvut.fel.constructa.model.Company;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Sort;

import java.util.List;

/**
 * The interface Company repository.
 */
@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
    /**
     * Find all list.
     *
     * @param sort the sort
     * @return the list
     */
    @NotNull
    List<Company> findAll(@NotNull Sort sort);
}

package cz.cvut.fel.constructa.repository;

import cz.cvut.fel.constructa.model.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * The interface Document repository.
 */
@Repository
public interface DocumentRepository extends JpaRepository<Document, Long> {
}

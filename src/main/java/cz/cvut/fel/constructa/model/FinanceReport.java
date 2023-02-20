package cz.cvut.fel.constructa.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "finance_reports")
@Getter
@Setter
@NoArgsConstructor
public class FinanceReport {
    @Id
    private Long id;
}

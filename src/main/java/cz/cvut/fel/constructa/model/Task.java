package cz.cvut.fel.constructa.model;

import cz.cvut.fel.constructa.enums.TaskState;
import cz.cvut.fel.constructa.model.role.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "task_id", nullable = false)
    private Long id;

    @Column(name = "date_of_creation")
    private Date dateOfCreation = new Date();

    @Column(name = "name")
    private String name;

    @Column(name="description")
    private String description;

    @Column(name="location_name")
    private String locationName;

    @Column(name="latitude")
    private Double latitude;

    @Column(name="longitude")
    private Double longitude;

    @Column(name = "time_from")
        private Date timeFrom;

    @Column(name="time_to")
    private Date timeTo;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private User author;

    @ManyToOne
    @JoinColumn(name = "assignee_id")
    private User assignee;

    @Enumerated(EnumType.STRING)
    private TaskState state;
}

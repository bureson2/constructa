package cz.cvut.fel.constructa.model;

import cz.cvut.fel.constructa.enums.TaskState;
import cz.cvut.fel.constructa.model.role.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.Date;

/**
 * Class representing a task.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tasks")
public class Task {
    /**
     * The identifier of the task.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "task_id", nullable = false)
    private Long id;

    /**
     * The date when the task was created.
     */
    @Column(name = "date_of_creation")
    private Date dateOfCreation = new Date();

    /**
     * The name of the task.
     */
    @Column(name = "name", nullable = false)
    private String name;

    /**
     * The description of the task.
     */
    @Column(name="description")
    private String description;

    /**
     * The name of the location associated with the task.
     */
    @Column(name="location_name")
    private String locationName;

    /**
     * The latitude of the location associated with the task.
     */
    @Column(name="latitude")
    private Double latitude;

    /**
     * The longitude of the location associated with the task.
     */
    @Column(name="longitude")
    private Double longitude;

    /**
     * The start time of the task.
     */
    @Column(name = "time_from")
        private Date timeFrom;

    /**
     * The end time of the task.
     */
    @Column(name="time_to")
    private Date timeTo;

    /**
     * The author of the task.
     */
    @ManyToOne
    @JoinColumn(name = "author_id")
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private User author;

    /**
     * The assignee of the task.
     */
    @ManyToOne
    @JoinColumn(name = "assignee_id")
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private User assignee;

    /**
     * The state of the task.
     */
    @Enumerated(EnumType.STRING)
    private TaskState state;
}

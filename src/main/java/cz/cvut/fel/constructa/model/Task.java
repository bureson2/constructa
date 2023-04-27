package cz.cvut.fel.constructa.model;

import cz.cvut.fel.constructa.enums.TaskState;
import cz.cvut.fel.constructa.model.role.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * The type Task.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tasks")
public class Task {
    /**
     * The Id.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "task_id", nullable = false)
    private Long id;

    /**
     * The Date of creation.
     */
    @Column(name = "date_of_creation")
    private Date dateOfCreation = new Date();

    /**
     * The Name.
     */
    @Column(name = "name")
    private String name;

    /**
     * The Description.
     */
    @Column(name="description")
    private String description;

    /**
     * The Location name.
     */
    @Column(name="location_name")
    private String locationName;

    /**
     * The Latitude.
     */
    @Column(name="latitude")
    private Double latitude;

    /**
     * The Longitude.
     */
    @Column(name="longitude")
    private Double longitude;

    /**
     * The Time from.
     */
    @Column(name = "time_from")
        private Date timeFrom;

    /**
     * The Time to.
     */
    @Column(name="time_to")
    private Date timeTo;

    /**
     * The Author.
     */
    @ManyToOne
    @JoinColumn(name = "author_id")
    private User author;

    /**
     * The Assignee.
     */
    @ManyToOne
    @JoinColumn(name = "assignee_id")
    private User assignee;

    /**
     * The State.
     */
    @Enumerated(EnumType.STRING)
    private TaskState state;
}

package cz.cvut.fel.constructa.model;

import cz.cvut.fel.constructa.enums.TaskState;
import cz.cvut.fel.constructa.enums.WorkReportType;
import cz.cvut.fel.constructa.model.role.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.geo.Point;

import java.util.Date;

@Entity
@Table(name = "tasks")
@Getter
@Setter
@NoArgsConstructor
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

    @Column(name="location_position")
    private Point locationPosition;

    @ManyToOne
    @JoinColumn(name = "location_id")
    private Location location;

    @Column(name = "time_from")
    private Date timeFrom;

    @Column(name="time_to")
    private Date timeTo;

    @ManyToOne
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User author;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User assignee;

    @Enumerated(EnumType.STRING)
    private TaskState state;
}

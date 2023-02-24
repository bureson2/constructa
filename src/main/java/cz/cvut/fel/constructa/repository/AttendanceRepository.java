package cz.cvut.fel.constructa.repository;

import cz.cvut.fel.constructa.model.report.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
}

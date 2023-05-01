package cz.cvut.fel.constructa.service;

import cz.cvut.fel.constructa.dto.request.AttendanceRequest;
import cz.cvut.fel.constructa.dto.request.IllnessRequest;
import cz.cvut.fel.constructa.dto.request.StopAttendanceRequest;
import cz.cvut.fel.constructa.dto.request.WorkReportRequest;
import cz.cvut.fel.constructa.dto.response.LocationDTO;
import cz.cvut.fel.constructa.dto.response.WorkReportDTO;
import cz.cvut.fel.constructa.enums.WorkReportType;
import cz.cvut.fel.constructa.mapper.LocationMapper;
import cz.cvut.fel.constructa.mapper.WorkReportMapper;
import cz.cvut.fel.constructa.model.Location;
import cz.cvut.fel.constructa.model.report.WorkReport;
import cz.cvut.fel.constructa.model.role.User;
import cz.cvut.fel.constructa.repository.LocationRepository;
import cz.cvut.fel.constructa.repository.UserRepository;
import cz.cvut.fel.constructa.repository.WorkReportRepository;
import cz.cvut.fel.constructa.security.AuthenticationFacade;
import cz.cvut.fel.constructa.service.interfaces.WorkReportService;
import cz.cvut.fel.constructa.service.util.DistanceCalculator;
import cz.cvut.fel.constructa.service.util.RoundTime;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * The type Work report service.
 */
@Service
@RequiredArgsConstructor
public class WorkReportServiceImpl implements WorkReportService {
    /**
     * The User dao.
     */
    private final UserRepository userDao;
    /**
     * The Work report dao.
     */
    private final WorkReportRepository workReportDao;
    /**
     * The Location dao.
     */
    private final LocationRepository locationDao;
    /**
     * The Work report mapper.
     */
    private final WorkReportMapper workReportMapper;
    /**
     * The Location mapper.
     */
    private final LocationMapper locationMapper;
    /**
     * The Authentication facade.
     */
    private final AuthenticationFacade authenticationFacade;

    /**
     * Create work report dto.
     *
     * @param request the request
     * @return the work report dto
     * @throws ParseException the parse exception
     */
    @Override
    public WorkReportDTO create(WorkReportRequest request) throws ParseException {
        WorkReport createdWorkReport = workReportMapper.convertToEntity(request);
        Optional<User> user = userDao.findById(request.getUserId());
        user.ifPresent(createdWorkReport::setReportingEmployee);
        createdWorkReport = workReportDao.save(createdWorkReport);
        return workReportMapper.convertToDto(createdWorkReport);
    }

    /**
     * Record illness.
     *
     * @param request the request
     */
    @Override
    public void recordIllness(IllnessRequest request) {

        String authorEmail = authenticationFacade.getAuthentication().getName();
        Optional<User> reportingEmployee = userDao.findByEmail(authorEmail);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(request.getTimeFrom());

        while (!calendar.getTime().after(request.getTimeTo())) {
            WorkReport workReport = new WorkReport();
            Location location = new Location();
            location.setCountry("");
            location.setCity("");
            location.setDescriptiveNumber("");
            location.setPostCode("");
            location.setStreet("");
            locationDao.save(location);

            workReport.setLocation(location);
            reportingEmployee.ifPresent(workReport::setReportingEmployee);
            Date timeFrom = calendar.getTime();
            timeFrom.setHours(10);
            timeFrom.setMinutes(0);
            workReport.setTimeFrom(timeFrom);
            Date timeTo = calendar.getTime();
            timeTo.setHours(18);
            timeTo.setMinutes(0);
            workReport.setTimeTo(timeTo);
            workReport.setMinutes(480);
            calendar.add(Calendar.DATE, 1);

            workReport.setType(WorkReportType.SICK_NOTE);

            workReportDao.save(workReport);
        }
    }

    /**
     * Record work report.
     *
     * @param request the request
     * @return the work report dto
     */
    @Override
    public WorkReportDTO recordWorkReport(AttendanceRequest request) {

        String authorEmail = authenticationFacade.getAuthentication().getName();
        Optional<User> reportingEmployee = userDao.findByEmail(authorEmail);

        Optional<Location> location = locationDao.findById(request.getLocationId());

        Date timeFrom = new Date();

        WorkReport workReport = new WorkReport();
        if(reportingEmployee.isPresent() && location.isPresent()){
            workReport.setType(WorkReportType.WORK_REPORT);
            workReport.setReportingEmployee(reportingEmployee.get());
            workReport.setLocation(location.get());
            workReport.setTimeFrom(timeFrom);
            workReport.setTimeTo(timeFrom);
            workReport = workReportDao.save(workReport);

            if(request.getLatitude() == null || request.getLongitude() == null){
//                notify manager about missing location
                return workReportMapper.convertToDto(workReport);
            }

            double metres = DistanceCalculator.haversineDistance(location.get().getLatitude(),location.get().getLongitude(), request.getLatitude(), request.getLongitude());
            if(metres < 100){
//                notify manager about wrong location
            }
        }

        return workReportMapper.convertToDto(workReport);
    }

    /**
     * Stop work report record.
     *
     * @param request the request
     */
    @Override
    public void stopWorkReportRecord(StopAttendanceRequest request){
        Date today = new Date();
        today.setHours(0);

        String authorEmail = authenticationFacade.getAuthentication().getName();
        Optional<User> reportingEmployee = userDao.findByEmail(authorEmail);
        if(reportingEmployee.isPresent()){

            List<WorkReport> workReports = workReportDao.findTodayAttendance(reportingEmployee.get().getId(), today);
            WorkReport workReport = workReports.get(0);

            workReport.setTimeTo(new Date());
            int minutes = request.getTime() / 60;
            minutes -= RoundTime.getRoundedUpMinutes(workReport.getTimeFrom());
            minutes -= RoundTime.getRoundedDownMinutes(new Date());

            workReport.setMinutes(RoundTime.setToQuarterHour(minutes));
            workReportDao.save(workReport);
        }
    }

    /**
     * Gets work report by id.
     *
     * @param id the id
     * @return the work report by id
     */
    @Override
    public WorkReportDTO getWorkReportById(Long id) {
        Optional<WorkReport> workReport = workReportDao.findAll().stream().filter(it -> it.getId().equals(id)).findFirst();
        return workReport.map(workReportMapper::convertToDto).orElse(null);
    }

    /**
     * Gets worklocation by id.
     *
     * @param id the id
     * @return the worklocation by id
     */
    @Override
    public LocationDTO getWorklocationById(Long id) {
        Optional<Location> location = locationDao.findAll().stream().filter(it -> it.getId().equals(id)).findFirst();
        return location.map(locationMapper::convertToDto).orElse(null);
    }

    /**
     * Gets work reports.
     *
     * @return the work reports
     */
    @Override
    public List<WorkReportDTO> getWorkReports() {
        List<WorkReport> workReports = workReportDao.findAll();
        return workReports.stream()
                        .map(workReportMapper::convertToDto)
                        .collect(Collectors.toList());
    }

    /**
     * Gets my work reports.
     *
     * @return my work reports
     */
    @Override
    public List<WorkReportDTO> getMyWorkReports() {
        Sort sortByDate = Sort.by(Sort.Direction.DESC, "timeTo");
        String authorEmail = authenticationFacade.getAuthentication().getName();
        Optional<User> author = userDao.findByEmail(authorEmail);
        List<WorkReport> reports = new ArrayList<>();

        if(author.isPresent()) {
            reports = workReportDao.findWorkReportsByReportingEmployeeId(author.get().getId(), sortByDate);
        }

        return reports.stream()
                .map(workReportMapper::convertToDto)
                .collect(Collectors.toList());
    }

    /**
     * Gets work reports by reporting employee id.
     *
     * @param id the id
     * @return the work reports by reporting employee id
     */
    @Override
    public List<WorkReportDTO> getWorkReportsByReportingEmployeeId(Long id) {
        Sort sortByDate = Sort.by(Sort.Direction.DESC, "timeTo");
        List<WorkReport> workReports = workReportDao.findWorkReportsByReportingEmployeeId(id, sortByDate);
        return workReports.stream()
                .map(workReportMapper::convertToDto)
                .collect(Collectors.toList());
    }

    /**
     * Delete.
     *
     * @param id the id
     */
    @Override
    public void delete(Long id) {
        workReportDao.deleteById(id);
    }

    /**
     * Update work report dto.
     *
     * @param request the request
     * @return the work report dto
     * @throws ParseException the parse exception
     */
    @Override
    public WorkReportDTO update(WorkReportRequest request) throws ParseException {
        Optional<WorkReport> report = workReportDao.findById(request.getId());
        User employee = null;
        Location location = null;

        if (report.isPresent()) {
            employee = report.get().getReportingEmployee();
            location = report.get().getLocation();
        }
        WorkReport updatedReport = workReportMapper.convertToEntity(request);
        updatedReport.setReportingEmployee(employee);
        updatedReport.setLocation(location);
        updatedReport = workReportDao.save(updatedReport);

        return workReportMapper.convertToDto(updatedReport);
    }
}

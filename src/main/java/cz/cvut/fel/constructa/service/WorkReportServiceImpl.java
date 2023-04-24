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
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WorkReportServiceImpl implements WorkReportService {
    private final UserRepository userDao;
    private final WorkReportRepository workReportDao;
    private final LocationRepository locationDao;
    private final WorkReportMapper workReportMapper;
    private final LocationMapper locationMapper;
    private final AuthenticationFacade authenticationFacade;

    @Override
    public WorkReportDTO create(WorkReportRequest request) throws ParseException {
        WorkReport createdWorkReport = workReportMapper.convertToEntity(request);
        Optional<User> user = userDao.findById(request.getUserId());
        user.ifPresent(createdWorkReport::setReportingEmployee);
        createdWorkReport = workReportDao.save(createdWorkReport);
        return workReportMapper.convertToDto(createdWorkReport);
    }

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
            workReport.setTimeFrom(calendar.getTime());
            calendar.add(Calendar.HOUR_OF_DAY, 8);
            Date timeTo = calendar.getTime();
            workReport.setTimeTo(timeTo);
            calendar.add(Calendar.HOUR_OF_DAY, -8);
            workReport.setMinutes(480);
            calendar.add(Calendar.DATE, 1);

            workReport.setType(WorkReportType.SICK_NOTE);

            workReportDao.save(workReport);
        }
    }

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

    @Override
    public void stopWorkReportRecord(StopAttendanceRequest request){
        Date today = new Date();
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTime(today);
//        calendar.add(Calendar.DATE, -1);
//        today = calendar.getTime();

        today.setHours(0);
        System.out.println(today);


//        LocalDate compareDate = today.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        String authorEmail = authenticationFacade.getAuthentication().getName();
        Optional<User> reportingEmployee = userDao.findByEmail(authorEmail);
        if(reportingEmployee.isPresent()){

            List<WorkReport> workReports = workReportDao.findTodayAttendance(reportingEmployee.get().getId(), today);
            WorkReport workReport = workReports.get(0);
//
//            System.out.println(request.getTime());
//
            workReport.setTimeTo(new Date());
            // todo check
            workReport.setMinutes(request.getTime() / 60);
            workReportDao.save(workReport);
        }
    }

    @Override
    public WorkReportDTO getWorkReportById(Long id) {
        Optional<WorkReport> workReport = workReportDao.findAll().stream().filter(it -> it.getId().equals(id)).findFirst();
        return workReport.map(workReportMapper::convertToDto).orElse(null);
    }

    @Override
    public LocationDTO getWorklocationById(Long id) {
        Optional<Location> location = locationDao.findAll().stream().filter(it -> it.getId().equals(id)).findFirst();
        return location.map(locationMapper::convertToDto).orElse(null);
    }

    @Override
    public List<WorkReportDTO> getWorkReports() {
        List<WorkReport> workReports = workReportDao.findAll();
        return workReports.stream()
                        .map(workReportMapper::convertToDto)
                        .collect(Collectors.toList());
    }

    @Override
    public List<WorkReportDTO> getMyWorkReports() {
        String authorEmail = authenticationFacade.getAuthentication().getName();
        Optional<User> author = userDao.findByEmail(authorEmail);
        List<WorkReport> reports = new ArrayList<>();

        if(author.isPresent()) {
            reports = workReportDao.findWorkReportsByReportingEmployeeId(author.get().getId());
        }

        return reports.stream()
                .map(workReportMapper::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<WorkReportDTO> getWorkReportsByReportingEmployeeId(Long id) {
        List<WorkReport> workReports = workReportDao.findWorkReportsByReportingEmployeeId(id);
        return workReports.stream()
                .map(workReportMapper::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        workReportDao.deleteById(id);
    }

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

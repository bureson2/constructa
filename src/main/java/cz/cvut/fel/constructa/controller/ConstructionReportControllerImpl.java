package cz.cvut.fel.constructa.controller;

import cz.cvut.fel.constructa.controller.interfaces.ConstructionReportController;
import cz.cvut.fel.constructa.dto.request.ConstructionReportRequest;
import cz.cvut.fel.constructa.dto.response.ConstructionReportDTO;
import cz.cvut.fel.constructa.security.AuthenticationFacade;
import cz.cvut.fel.constructa.service.interfaces.ConstructionReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * The type Construction report controller.
 */
@CrossOrigin
@RestController
@RequestMapping("/api/v1/construction-reports")
@RequiredArgsConstructor
public class ConstructionReportControllerImpl implements ConstructionReportController {
    /**
     * The Authentication facade.
     */
    private final AuthenticationFacade authenticationFacade;

    /**
     * The Construction report service.
     */
    private final ConstructionReportService constructionReportService;

    /**
     * Has permission boolean.
     *
     * @param requiredRoles the required roles
     * @return the boolean
     */
    private boolean hasPermission(List<GrantedAuthority> requiredRoles){
        Authentication authentication = authenticationFacade.getAuthentication();
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        System.out.println(authorities.toArray()[0]);
        return authorities.stream().anyMatch(requiredRoles::contains);
    }

    /**
     * Gets construction reports.
     *
     * @return the construction reports
     */
    @Override
    @ResponseStatus(code = HttpStatus.OK)
    @ResponseBody
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ConstructionReportDTO>> getConstructionReports() {
        return ResponseEntity.ok().body(
                constructionReportService.getConstructionReports()
        );
    }

    /**
     * Gets construction reports by project id.
     *
     * @param projectId the project id
     * @return the construction reports by project id
     */
    @Override
    @ResponseStatus(code = HttpStatus.OK)
    @ResponseBody
    @GetMapping(value="project/{projectId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ConstructionReportDTO>> getConstructionReportsByProjectId(@PathVariable Long projectId) {
        return ResponseEntity.ok().body(
                constructionReportService.getConstructionReportsByProjectId(projectId)
        );
    }

    /**
     * Gets construction report.
     *
     * @param constructionReportId the construction report id
     * @return the construction report
     */
    @Override
    @ResponseStatus(code = HttpStatus.OK)
    @ResponseBody
    @GetMapping(value = "/{constructionReportId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ConstructionReportDTO> getConstructionReport(@PathVariable Long constructionReportId) {
        ConstructionReportDTO constructionReport = constructionReportService.getConstructionReporttById(constructionReportId);
        if(constructionReport != null){
            return ResponseEntity.ok().body(
                    constructionReport
            );
        }
        return ResponseEntity.notFound().build();
    }

    /**
     * Create construction report response entity.
     *
     * @param request the request
     * @return the response entity
     * @throws ParseException the parse exception
     */
    @Override
    @ResponseStatus(code = HttpStatus.CREATED)
    @ResponseBody
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ConstructionReportDTO> createConstructionReport(@RequestBody ConstructionReportRequest request) throws ParseException {
        List<GrantedAuthority> requiredRoles = Arrays.asList(
                new SimpleGrantedAuthority("ROLE_ADMIN"),
                new SimpleGrantedAuthority("ROLE_MANAGER"),
                new SimpleGrantedAuthority("ROLE_CONSTRUCTION_MANAGER")
        );

        if (hasPermission(requiredRoles)) {
            return ResponseEntity.ok().body(
                    constructionReportService.create(request)
            );
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }

    /**
     * Update construction report response entity.
     *
     * @param request the request
     * @return the response entity
     * @throws ParseException the parse exception
     */
    @Override
    @ResponseStatus(code = HttpStatus.CREATED)
    @ResponseBody
    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ConstructionReportDTO> updateConstructionReport(@RequestBody ConstructionReportRequest request) throws ParseException {
        List<GrantedAuthority> requiredRoles = Arrays.asList(
                new SimpleGrantedAuthority("ROLE_ADMIN"),
                new SimpleGrantedAuthority("ROLE_MANAGER"),
                new SimpleGrantedAuthority("ROLE_CONSTRUCTION_MANAGER")
        );

        if (hasPermission(requiredRoles)) {
            return ResponseEntity.ok().body(
                    constructionReportService.update(request)
            );
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }

    /**
     * Delete construction report response entity.
     *
     * @param constructionReportId the construction report id
     * @return the response entity
     */
    @Override
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "/{constructionReportId}")
    public ResponseEntity<Void> deleteConstructionReport(@PathVariable Long constructionReportId) {
        List<GrantedAuthority> requiredRoles = Arrays.asList(
                new SimpleGrantedAuthority("ROLE_ADMIN"),
                new SimpleGrantedAuthority("ROLE_MANAGER"),
                new SimpleGrantedAuthority("ROLE_CONSTRUCTION_MANAGER")
        );

        if (hasPermission(requiredRoles)) {
            constructionReportService.delete(constructionReportId);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }
}

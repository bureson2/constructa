package cz.cvut.fel.constructa.service;

import cz.cvut.fel.constructa.dto.request.ConstructionReportRequest;
import cz.cvut.fel.constructa.dto.response.ConstructionReportDTO;
import cz.cvut.fel.constructa.mapper.ConstructionReportMapper;
import cz.cvut.fel.constructa.model.report.ConstructionReport;
import cz.cvut.fel.constructa.repository.ConstructionReportRepository;
import cz.cvut.fel.constructa.service.interfaces.ConstructionReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ConstructionReportImpl implements ConstructionReportService {
    private final ConstructionReportRepository constructionReportDao;
    private final ConstructionReportMapper constructionReportMapper;
    @Override
    public ConstructionReport create(ConstructionReportRequest request) throws ParseException {
        ConstructionReport constructionReport = constructionReportMapper.convertToEntity(request);
        return constructionReportDao.save(constructionReport);
    }

    @Override
    public ConstructionReportDTO getConstructionReporttById(Long id) {
        Optional<ConstructionReport> constructionReport = constructionReportDao.findById(id);
        return constructionReport.map(constructionReportMapper::convertToDto).orElse(null);
    }

    @Override
    public List<ConstructionReportDTO> getConstructionReports() {
        List<ConstructionReport> constructionReports = constructionReportDao.findAll();
        return constructionReports.stream()
                .map(constructionReportMapper::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        constructionReportDao.deleteById(id);
    }

    @Override
    public ConstructionReport update(ConstructionReportRequest constructionReport) {
        return null;
    }
}

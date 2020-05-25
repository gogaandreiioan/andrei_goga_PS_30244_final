package sample.service.report;

import org.springframework.stereotype.Service;
import sample.dto.PatientDto;
import sample.report.ReportStrategy;

import java.io.IOException;
import java.util.List;

@Service
public class ReportServiceImpl implements ReportService {

    private ReportStrategy reportStrategy;

    public void setReportStrategy(ReportStrategy reportStrategy) {
        this.reportStrategy = reportStrategy;
    }

    public void generateReport(List<PatientDto> patients) throws IOException {
        reportStrategy.createReport(patients);
    }
}
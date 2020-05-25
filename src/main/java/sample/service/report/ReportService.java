package sample.service.report;

import sample.dto.PatientDto;
import sample.report.ReportStrategy;

import java.io.IOException;
import java.util.List;

public interface ReportService{

    void generateReport(List<PatientDto> patients) throws IOException;
    void setReportStrategy(ReportStrategy reportStrategy);
}
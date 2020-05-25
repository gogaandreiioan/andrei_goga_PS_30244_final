package sample.report;

import sample.dto.PatientDto;

import java.io.IOException;
import java.util.List;

public interface ReportStrategy {
    void createReport(List<PatientDto> patients) throws IOException;
}

package sample.report;

import sample.dto.PatientDto;

import java.io.*;
import java.util.List;

public class CSVStrategy implements ReportStrategy {

    private static int index = 0;

    @Override
    public void createReport(List<PatientDto> patients) throws IOException {
        File file = new File("my_doc_" + index++ + ".csv");
        PrintWriter pw = new PrintWriter(file);
        StringBuilder stringBuilder = new StringBuilder();

        for (PatientDto patient : patients){

            stringBuilder
                    .append(patient.getName())
                    .append(",")
                    .append(patient.getAddress())
                    .append(",")
                    .append(patient.getDateOfBirth())
                    .append(",")
                    .append(patient.getPersonalNumber())
                    .append(",");

            pw.write(stringBuilder.toString());
            pw.close();
        }
    }
}
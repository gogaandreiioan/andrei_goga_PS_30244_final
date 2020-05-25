package sample.report;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import sample.dto.PatientDto;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class PDFStrategy implements ReportStrategy {

    private static int index = 0;

    @Override
    public void createReport(List<PatientDto> patients) throws IOException {

        PDDocument doc = new PDDocument();
        PDPage page = new PDPage();
        doc.addPage(page);
        //Creating a PDF Document

        PDPageContentStream contentStream = new PDPageContentStream(doc, page);

        //Begin the Content stream
        contentStream.beginText();

        //Setting the font to the Content stream
        contentStream.setFont(PDType1Font.TIMES_ROMAN, 16);

        //Setting the leading
        contentStream.setLeading(14.5f);

        //Setting the position for the line
        contentStream.newLineAtOffset(25, 725);

        //Adding text in the form of string
        for (PatientDto patient : patients) {
            contentStream.showText("Name: " + patient.getName());
            contentStream.newLine();
            contentStream.showText("    Address: " + patient.getAddress());
            contentStream.newLine();
            contentStream.showText("    Date Of Birth: " + patient.getDateOfBirth());
            contentStream.newLine();
            contentStream.showText("    Personal Number: " + patient.getPersonalNumber());
            contentStream.newLine();
            contentStream.newLine();
        }
        //Ending the content stream
        contentStream.endText();

        //Closing the content stream
        contentStream.close();

        //Saving the document
        doc.save(new File("my_doc_" + index++ + ".pdf"));

        //Closing the document
        doc.close();
    }
}

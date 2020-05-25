package sample.notification;

import java.util.Date;

public class OutputMessage
{
    private String patientName;
    private String doctorName;
    private Date dateOfConsultation;

    public OutputMessage() {}

    public OutputMessage(String patientName, String doctorName, Date dateOfConsultation) {
        this.patientName = patientName;
        this.doctorName = doctorName;
        this.dateOfConsultation = dateOfConsultation;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public Date getDateOfConsultation() {
        return dateOfConsultation;
    }

    public void setDateOfConsultation(Date dateOfConsultation) {
        this.dateOfConsultation = dateOfConsultation;
    }
}

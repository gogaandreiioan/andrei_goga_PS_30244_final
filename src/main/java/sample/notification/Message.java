package sample.notification;

public class Message
{
    private String patientPersonalNumber;
    private String doctorUsername;
    private String dateOfConsultation;
    public Message() {}

    public Message(String patientPersonalNumber, String doctorUsername, String dateOfConsultation) {
        this.patientPersonalNumber = patientPersonalNumber;
        this.doctorUsername = doctorUsername;
        this.dateOfConsultation = dateOfConsultation;
    }

    public String getPatientPersonalNumber() {
        return patientPersonalNumber;
    }

    public void setPatientPersonalNumber(String patientPersonalNumber) {
        this.patientPersonalNumber = patientPersonalNumber;
    }

    public String getDoctorUsername() {
        return doctorUsername;
    }

    public void setDoctorUsername(String doctorUsername) {
        this.doctorUsername = doctorUsername;
    }

    public String getDateOfConsultation() {
        return dateOfConsultation;
    }

    public void setDateOfConsultation(String dateOfConsultation) {
        this.dateOfConsultation = dateOfConsultation;
    }
}

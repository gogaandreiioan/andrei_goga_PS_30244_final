package sample.entity.builder;

import sample.entity.Consultation;
import sample.entity.Patient;
import sample.entity.User;

import java.util.Date;

public class ConsultationBuilder {

    private Consultation consultation;

    public ConsultationBuilder() {
        this.consultation = new Consultation();
    }

    public ConsultationBuilder setDateOfConsultation(Date dateOfConsultation){
        consultation.setDateOfConsultation(dateOfConsultation);
        return this;
    }

    public ConsultationBuilder setPatient(Patient patient){
        consultation.setPatient(patient);
        return this;
    }

    public ConsultationBuilder setDoctor(User doctor){
        consultation.setDoctor(doctor);
        return this;
    }

    public ConsultationBuilder setDescription(String description){
        consultation.setDescription(description);
        return this;
    }

    public Consultation build(){
        return consultation;
    }
}

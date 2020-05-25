package sample.entity.builder;

import sample.entity.Bed;
import sample.entity.Hospitalization;
import sample.entity.Patient;

import java.util.Date;

public class HospitalizationBuilder {

    private Hospitalization hospitalization;

    public HospitalizationBuilder() {
        hospitalization = new Hospitalization();
    }

    public HospitalizationBuilder setPatient(Patient patient) {
        hospitalization.setPatient(patient);
        return this;
    }

    public HospitalizationBuilder setBed(Bed bed){
        hospitalization.setBed(bed);
        return this;
    }

    public HospitalizationBuilder setAdmissionDate(Date admissionDate) {
        hospitalization.setAdmissionDate(admissionDate);
        return this;
    }

    public HospitalizationBuilder setReleaseDate(Date releaseDate) {
        hospitalization.setReleaseDate(releaseDate);
        return this;
    }

    public Hospitalization build() {
        return hospitalization;
    }
}

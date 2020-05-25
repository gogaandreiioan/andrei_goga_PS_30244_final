package sample.entity.builder;

import sample.entity.Patient;

import java.util.Date;

public class PatientBuilder {

    private Patient patient;

    public PatientBuilder() {
        this.patient = new Patient();
    }

    public PatientBuilder setIdentityNumber(Long identityNumber){
        patient.setIdentityNumber(identityNumber);
        return this;
    }

    public PatientBuilder setPersonalNumber(Long personalNumber){
        patient.setPersonalNumber(personalNumber);
        return this;
    }

    public PatientBuilder setAddress(String address){
        patient.setAddress(address);
        return this;
    }

    public PatientBuilder setName(String name){
        patient.setName(name);
        return this;
    }

    public PatientBuilder setDateOfBirth(Date dateOfBirth){
        patient.setDateOfBirth(dateOfBirth);
        return this;
    }

    public Patient build(){
        return patient;
    }
}

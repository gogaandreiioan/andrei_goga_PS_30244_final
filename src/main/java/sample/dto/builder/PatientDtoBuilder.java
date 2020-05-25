package sample.dto.builder;

import sample.dto.PatientDto;

import java.util.Date;

public class PatientDtoBuilder {

    private PatientDto patientDto;

    public PatientDtoBuilder(){
        patientDto = new PatientDto();
    }

    public PatientDtoBuilder setIdentityNumber(Long identityNumber){
        patientDto.setIdentityNumber(identityNumber);
        return this;
    }

    public PatientDtoBuilder setPersonalNumber(Long personalNumber){
        patientDto.setPersonalNumber(personalNumber);
        return this;
    }

    public PatientDtoBuilder setAddress(String address){
        patientDto.setAddress(address);
        return this;
    }

    public PatientDtoBuilder setName(String name){
        patientDto.setName(name);
        return this;
    }

    public PatientDtoBuilder setDateOfBirth(Date dateOfBirth){
        patientDto.setDateOfBirth(dateOfBirth);
        return this;
    }

    public PatientDto build(){
        return patientDto;
    }


}

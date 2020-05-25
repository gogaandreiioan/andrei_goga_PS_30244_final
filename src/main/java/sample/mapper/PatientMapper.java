package sample.mapper;

import sample.dto.PatientDto;
import sample.dto.builder.PatientDtoBuilder;
import sample.entity.Patient;
import sample.entity.builder.PatientBuilder;

public class PatientMapper implements Mapper<Patient, PatientDto> {

    @Override
    public Patient mapTo(PatientDto patientDto) {
        return new PatientBuilder()
                .setAddress(patientDto.getAddress())
                .setDateOfBirth(patientDto.getDateOfBirth())
                .setIdentityNumber(patientDto.getIdentityNumber())
                .setName(patientDto.getName())
                .setPersonalNumber(patientDto.getPersonalNumber())
                .build();

    }

    @Override
    public PatientDto mapFrom(Patient patient) {
        return new PatientDtoBuilder()
                .setAddress(patient.getAddress())
                .setDateOfBirth(patient.getDateOfBirth())
                .setIdentityNumber(patient.getIdentityNumber())
                .setName(patient.getName())
                .setPersonalNumber(patient.getPersonalNumber())
                .build();
    }
}

package sample.dto.builder;

import sample.dto.ConsultationDto;
import sample.dto.PatientDto;
import sample.dto.UserDto;

import java.util.Date;

public class ConsultationDtoBuilder {

    private ConsultationDto consultationDto;

    public ConsultationDtoBuilder() {
        this.consultationDto = new ConsultationDto();
        setDescription("");
    }

    public ConsultationDtoBuilder setDateOfConsultation(Date dateOfConsultation){
        consultationDto.setDateOfConsultation(dateOfConsultation);
        return this;
    }

    public ConsultationDtoBuilder setPatient(PatientDto patient){
        consultationDto.setPatient(patient);
        return this;
    }

    public ConsultationDtoBuilder setDoctor(UserDto doctor){
        consultationDto.setDoctor(doctor);
        return this;
    }

    public ConsultationDtoBuilder setDescription(String description){
        consultationDto.setDescription(description);
        return this;
    }

    public ConsultationDto build(){
        return consultationDto;
    }

}

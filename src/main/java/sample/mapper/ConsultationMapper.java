package sample.mapper;

import sample.dto.ConsultationDto;
import sample.dto.PatientDto;
import sample.dto.UserDto;
import sample.dto.builder.ConsultationDtoBuilder;
import sample.entity.Consultation;
import sample.entity.Patient;
import sample.entity.User;
import sample.entity.builder.ConsultationBuilder;

public class ConsultationMapper implements Mapper<Consultation, ConsultationDto> {

    private Mapper mapper = new UserMapper();
    private Mapper mapper1 = new PatientMapper();

    @Override
    public Consultation mapTo(ConsultationDto consultationDto) {
        return new ConsultationBuilder()
                .setDateOfConsultation(consultationDto.getDateOfConsultation())
                .setDoctor((User)mapper.mapTo(consultationDto.getDoctor()))
                .setDescription(consultationDto.getDescription())
                .setPatient((Patient)mapper1.mapTo(consultationDto.getPatient()))
                .build();
    }

    @Override
    public ConsultationDto mapFrom(Consultation consultation) {
        return new ConsultationDtoBuilder()
                .setDateOfConsultation(consultation.getDateOfConsultation())
                .setDescription(consultation.getDescription())
                .setDoctor((UserDto)mapper.mapFrom(consultation.getDoctor()))
                .setPatient((PatientDto)mapper1.mapFrom(consultation.getPatient()))
                .build();
    }
}

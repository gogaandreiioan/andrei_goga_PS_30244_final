package sample.service.consultation;

import sample.dto.ConsultationDto;
import sample.dto.PatientDto;
import sample.dto.UserDto;

import java.util.Date;
import java.util.List;

public interface ConsultationService {

    boolean create(ConsultationDto consultationDto);
    ConsultationDto findByDoctorAndPatientAndDateOfConsultation(UserDto doctor, PatientDto patientDto, Date dateOfConsultation);
    List<ConsultationDto> findByPatient(PatientDto patientDto);
    boolean update(ConsultationDto consultationDto, String newDescription);

}

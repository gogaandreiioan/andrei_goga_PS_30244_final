package sample.service.consultation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sample.dto.ConsultationDto;
import sample.dto.PatientDto;
import sample.dto.UserDto;
import sample.entity.Consultation;
import sample.entity.Patient;
import sample.entity.User;
import sample.mapper.ConsultationMapper;
import sample.mapper.Mapper;
import sample.repository.ConsultationRepository;
import sample.service.patient.PatientService;
import sample.service.user.UserService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ConsultationServiceImpl implements ConsultationService {

    private ConsultationRepository consultationRepository;
    private UserService userService;
    private PatientService patientService;
    private Mapper mapper;

    @Autowired
    public ConsultationServiceImpl(ConsultationRepository consultationRepository, UserService userService, PatientService patientService) {
        this.consultationRepository = consultationRepository;
        this.userService = userService;
        this.patientService = patientService;
        this.mapper = new ConsultationMapper();
    }

    @Override
    public boolean create(ConsultationDto consultationDto) {
        Consultation consultation = (Consultation)mapper.mapTo(consultationDto);
        consultation.setDoctor(userService.findByUsernameInternal(consultation.getDoctor().getUsername()));
        consultation.setPatient(patientService.findByPersonalNumberInternal(consultation.getPatient().getPersonalNumber()));
        consultationRepository.save(consultation);
        return true;
    }

    @Override
    public boolean update(ConsultationDto consultationDto, String newDescription) {
        Patient patient = patientService.findByPersonalNumberInternal(consultationDto.getPatient().getPersonalNumber());
        User doctor = userService.findByUsernameInternal(consultationDto.getDoctor().getUsername());
        Consultation consultation = consultationRepository.findByDateOfConsultationAndDoctorAndPatient(consultationDto.getDateOfConsultation(), doctor, patient);
        consultationRepository.updateDescription(newDescription, consultation.getId());
        return true;
    }

    @Override
    public ConsultationDto findByDoctorAndPatientAndDateOfConsultation(UserDto doctorDto, PatientDto patientDto, Date dateOfConsultation) {
        Patient patient = patientService.findByPersonalNumberInternal(patientDto.getPersonalNumber());
        User doctor = userService.findByUsernameInternal(doctorDto.getUsername());
        Consultation consultation = consultationRepository.findByDateOfConsultationAndDoctorAndPatient(dateOfConsultation,doctor,patient);
        if (consultation != null)
            return (ConsultationDto)mapper.mapFrom(consultation);
        else return null;
    }

    @Override
    public List<ConsultationDto> findByPatient(PatientDto patientDto) {
        Patient patient = patientService.findByPersonalNumberInternal(patientDto.getPersonalNumber());
        if (patient != null) {
            List<Consultation> consultations = consultationRepository.findByPatientAndDateOfConsultationBefore(patient, new Date());
            List<ConsultationDto> consultationDtos = new ArrayList<>();
            for (Consultation consultation : consultations) {
                consultationDtos.add((ConsultationDto) mapper.mapFrom(consultation));
            }
            return consultationDtos;
        }
        else return new ArrayList<>();
    }
}

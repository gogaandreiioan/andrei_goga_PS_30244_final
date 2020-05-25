package service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import sample.dto.ConsultationDto;
import sample.dto.PatientDto;
import sample.dto.UserDto;
import sample.entity.Consultation;
import sample.entity.Patient;
import sample.entity.User;
import sample.entity.builder.ConsultationBuilder;
import sample.entity.builder.PatientBuilder;
import sample.entity.builder.UserBuilder;
import sample.mapper.ConsultationMapper;
import sample.mapper.Mapper;
import sample.mapper.PatientMapper;
import sample.mapper.UserMapper;
import sample.repository.ConsultationRepository;
import sample.repository.PatientRepository;
import sample.repository.UserRepository;
import sample.service.consultation.ConsultationService;
import sample.service.consultation.ConsultationServiceImpl;
import sample.service.patient.PatientServiceImpl;
import sample.service.user.UserServiceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mockito.ArgumentMatchers.matches;
import static org.mockito.Mockito.only;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class ConsultationTest {

    @Mock
    ConsultationRepository consultationRepository;
    @Mock
    PatientRepository patientRepository;
    @Mock
    UserRepository userRepository;
    @InjectMocks
    PatientServiceImpl patientService;
    @InjectMocks
    UserServiceImpl userService;

    @InjectMocks
    ConsultationServiceImpl consultationService = new ConsultationServiceImpl(consultationRepository, userService, patientService);

    Consultation consultation;
    Patient patient = new PatientBuilder()
            .setPersonalNumber(1231231l)
            .setIdentityNumber(13212434l)
            .setDateOfBirth(new Date())
            .setAddress("daso")
            .setName("andrei")
            .build();
    User user = new UserBuilder()
            .setEmail("andrei@test.com")
            .setUsername("gogaandrei")
            .setPassword("Test1234")
            .setRole("Doctor")
            .build();

    Mapper consultationMapper = new ConsultationMapper();
    Mapper userMapper = new UserMapper();
    Mapper patientMapper = new PatientMapper();

    @Before
    public void setUp() throws Exception {

        Consultation consultation = new ConsultationBuilder()
                .setPatient(patient)
                .setDoctor(user)
                .setDateOfConsultation(new Date())
                .setDescription(new String())
                .build();
        List<Consultation> consultationList = new ArrayList<>();
        consultationList.add(consultation);

        when(consultationRepository.findByDateOfConsultationAndDoctorAndPatient(new Date(), user, patient)).thenReturn(consultation);
        when(consultationRepository.findByPatientAndDateOfConsultationBefore(patient, new Date())).thenReturn(consultationList);
        when(patientRepository.findByPersonalNumber(1231231l)).thenReturn(patient);
        when(userRepository.findByUsername("gogaandrei")).thenReturn(user);
    }

    @Test
    public void findByDateOfConsultationAndDoctorAndPatientTest(){
        UserDto userDto = (UserDto)userMapper.mapFrom(user);
        PatientDto patientDto = (PatientDto)patientMapper.mapFrom(patient);
        ConsultationDto consultationDto = consultationService.findByDoctorAndPatientAndDateOfConsultation(userDto, patientDto, new Date());
        Consultation consultation = (Consultation)consultationMapper.mapTo(consultationDto);
        Assert.assertEquals(consultation, this.consultation);
    }
}

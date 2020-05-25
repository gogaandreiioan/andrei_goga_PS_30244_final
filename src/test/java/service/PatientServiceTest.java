package service;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import sample.dto.PatientDto;
import sample.entity.Patient;
import sample.entity.builder.PatientBuilder;
import sample.mapper.Mapper;
import sample.mapper.PatientMapper;
import sample.repository.PatientRepository;
import sample.service.patient.PatientServiceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.only;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PatientServiceTest {

    @Mock
    PatientRepository patientRepository;

    @InjectMocks
    PatientServiceImpl patientService;

    Patient patient;
    Mapper mapper = new PatientMapper();

    @Before
    public void setUp() throws Exception {
         patient = new PatientBuilder()
                .setName("andrei")
                .setPersonalNumber(1234123l)
                .setAddress("Str. Tulcea")
                .setDateOfBirth(new Date())
                .setIdentityNumber(1234121l)
                .build();

        List<Patient> patientList = new ArrayList<>();
        patientList.add(patient);
        when(patientRepository.findAll()).thenReturn(patientList);
    }

    @Test
    public void getAll() {
        Assert.assertEquals(patientService.findAll().size(),1);
    }

    @Test
    public void save() {
        Assert.assertTrue(patientService.create((PatientDto) mapper.mapFrom(patient)));
    }

    @Test
    public void update() {
        Assert.assertTrue(patientService.update((PatientDto)mapper.mapFrom(patient), new String()));
    }
}
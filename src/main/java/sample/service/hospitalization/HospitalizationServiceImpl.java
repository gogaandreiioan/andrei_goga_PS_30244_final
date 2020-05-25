package sample.service.hospitalization;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sample.dto.BedDto;
import sample.dto.HospitalizationDto;
import sample.dto.PatientDto;
import sample.entity.Bed;
import sample.entity.Hospitalization;
import sample.entity.Patient;
import sample.entity.Saloon;
import sample.mapper.BedMapper;
import sample.mapper.HospitalizationMapper;
import sample.repository.BedRepository;
import sample.repository.HospitalizationRepository;
import sample.repository.PatientRepository;
import sample.repository.SaloonRepository;
import sample.utils.DateUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class HospitalizationServiceImpl implements HospitalizationService {

    private HospitalizationRepository hospitalizationRepository;
    private PatientRepository patientRepository;
    private BedRepository bedRepository;
    private SaloonRepository saloonRepository;
    private HospitalizationMapper hospitalizationMapper;

    @Autowired
    public HospitalizationServiceImpl(HospitalizationRepository hospitalizationRepository, PatientRepository patientRepository, BedRepository bedRepository, SaloonRepository saloonRepository) {
        this.hospitalizationMapper = new HospitalizationMapper();
        this.hospitalizationMapper.setBedMapper(new BedMapper());
        this.hospitalizationRepository = hospitalizationRepository;
        this.patientRepository = patientRepository;
        this.bedRepository = bedRepository;
        this.saloonRepository = saloonRepository;
    }

    @Override
    public void create(HospitalizationDto hospitalizationDto) {
        Hospitalization hospitalization = hospitalizationMapper.mapTo(hospitalizationDto);
        Saloon saloon = saloonRepository.findByNumber(hospitalizationDto.getBed().getSaloon().getNumber());
        Bed bed = bedRepository.findByNumberAndSaloon(hospitalizationDto.getBed().getNumber(), saloon);
        Patient patient = patientRepository.findByPersonalNumber(hospitalizationDto.getPatient().getPersonalNumber());
        hospitalization.setBed(bed);
        hospitalization.setPatient(patient);
        hospitalization.setAdmissionDate(DateUtils.trim(new Date()));
        hospitalizationRepository.save(hospitalization);
        bedRepository.updateOccupied(true, bed.getId());
    }

    @Override
    public HospitalizationDto findByPatientAndAdmissionDate(PatientDto patientDto, Date admissionDate) {
        Patient patient = patientRepository.findByPersonalNumber(patientDto.getPersonalNumber());
        if (patient == null || admissionDate == null)
            return null;
        return hospitalizationMapper.mapFrom(hospitalizationRepository.findFirstByPatientAndAdmissionDateAndReleaseDateNull(patient, admissionDate));
    }

    @Override
    public List<HospitalizationDto> findAllByPatient(PatientDto patientDto) {
        List<HospitalizationDto> hospitalizationDtos = new ArrayList<>();
        Patient patient = patientRepository.findByPersonalNumber(patientDto.getPersonalNumber());
        if (patient == null)
            return hospitalizationDtos;
        for (Hospitalization hospitalization : hospitalizationRepository.findAllByPatient(patient)) {
            hospitalizationDtos.add(hospitalizationMapper.mapFrom(hospitalization));
        }
        return hospitalizationDtos;
    }

    @Override
    public List<HospitalizationDto> findAllByBed(BedDto bedDto) {
        List<HospitalizationDto> hospitalizationDtos = new ArrayList<>();
        Saloon saloon = saloonRepository.findByNumber(bedDto.getSaloon().getNumber());
        if (saloon == null)
            return hospitalizationDtos;
        Bed bed = bedRepository.findByNumberAndSaloon(bedDto.getNumber(), saloon);
        if (bed == null)
            return hospitalizationDtos;
        for (Hospitalization hospitalization : hospitalizationRepository.findAllByBed(bed)) {
            hospitalizationDtos.add(hospitalizationMapper.mapFrom(hospitalization));
        }
        return hospitalizationDtos;
    }

    @Override
    public void release(HospitalizationDto hospitalizationDto) {
        Patient patient = patientRepository.findByPersonalNumber(hospitalizationDto.getPatient().getPersonalNumber());
        if (patient == null)
            return;
        Hospitalization hospitalization = hospitalizationRepository.findFirstByPatientAndAdmissionDateAndReleaseDateNull(patient, hospitalizationDto.getAdmissionDate());
        if (hospitalization == null)
            return;
        hospitalizationRepository.release(hospitalizationDto.getReleaseDate(), hospitalization.getId());
        bedRepository.updateOccupied(false, hospitalization.getBed().getId());
    }

    @Override
    public List<HospitalizationDto> findAllByPatientAndReleaseDateBefore(PatientDto patientDto, Date releaseDate) {
        List<HospitalizationDto> hospitalizationDtos = new ArrayList<>();
        Patient patient = patientRepository.findByPersonalNumber(patientDto.getPersonalNumber());
        if (patient == null)
            return hospitalizationDtos;
        for (Hospitalization hospitalization : hospitalizationRepository.findAllByPatientAndReleaseDateBefore(patient, releaseDate)) {
            hospitalizationDtos.add(hospitalizationMapper.mapFrom(hospitalization));
        }
        return hospitalizationDtos;
    }

    @Override
    public List<HospitalizationDto> findOngoing() {
        List<HospitalizationDto> hospitalizationDtos = new ArrayList<>();
        for (Hospitalization hospitalization : hospitalizationRepository.findAllByReleaseDateIsNull()) {
            hospitalizationDtos.add(hospitalizationMapper.mapFrom(hospitalization));
        }
        return hospitalizationDtos;
    }
}

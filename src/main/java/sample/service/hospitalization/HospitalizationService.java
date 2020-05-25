package sample.service.hospitalization;

import org.springframework.data.repository.query.Param;
import sample.dto.BedDto;
import sample.dto.HospitalizationDto;
import sample.dto.PatientDto;
import sample.entity.Hospitalization;

import java.util.Date;
import java.util.List;

public interface HospitalizationService {

    void create(HospitalizationDto hospitalizationDto);
    HospitalizationDto findByPatientAndAdmissionDate(PatientDto patientDto, Date admissionDate);
    List<HospitalizationDto> findAllByPatient(PatientDto patientDto);
    List<HospitalizationDto> findAllByBed(BedDto bedDto);
    void release(HospitalizationDto hospitalizationDto);
    List<HospitalizationDto> findAllByPatientAndReleaseDateBefore(PatientDto patientDto, Date releaseDate);
    List<HospitalizationDto> findOngoing();

}

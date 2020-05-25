package sample.dto.builder;

import sample.dto.BedDto;
import sample.dto.HospitalizationDto;
import sample.dto.PatientDto;

import java.util.Date;

public class HospitalizationDtoBuilder {

    private HospitalizationDto hospitalizationDto;

    public HospitalizationDtoBuilder() {
        hospitalizationDto = new HospitalizationDto();
    }

    public HospitalizationDtoBuilder setPatient(PatientDto patient) {
        hospitalizationDto.setPatient(patient);
        return this;
    }

    public HospitalizationDtoBuilder setBed(BedDto bed) {
        hospitalizationDto.setBed(bed);
        return this;
    }

    public HospitalizationDtoBuilder setAdmissionDate(Date admissionDate) {
        hospitalizationDto.setAdmissionDate(admissionDate);
        return this;
    }

    public HospitalizationDtoBuilder setReleaseDate(Date releaseDate) {
        hospitalizationDto.setReleaseDate(releaseDate);
        return this;
    }

    public HospitalizationDto build() {
        return hospitalizationDto;
    }
}

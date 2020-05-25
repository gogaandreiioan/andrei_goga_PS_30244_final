package sample.dto;

import javax.validation.constraints.NotNull;
import java.util.Set;

public class BedDto {

    @NotNull
    private int number;
    private Set<HospitalizationDto> hospitalizations;
    @NotNull
    private SaloonDto saloonDto;
    private Boolean isOccupied;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Set<HospitalizationDto> getHospitalizations() {
        return hospitalizations;
    }

    public void setHospitalization(Set<HospitalizationDto> hospitalizations) {
        this.hospitalizations = hospitalizations;
    }

    public SaloonDto getSaloon() {
        return saloonDto;
    }

    public void setSaloon(SaloonDto saloon) {
        this.saloonDto = saloon;
    }

    public SaloonDto getSaloonDto() {
        return saloonDto;
    }

    public void setSaloonDto(SaloonDto saloonDto) {
        this.saloonDto = saloonDto;
    }

    public Boolean getOccupied() {
        return isOccupied;
    }

    public void setOccupied(Boolean occupied) {
        isOccupied = occupied;
    }
}

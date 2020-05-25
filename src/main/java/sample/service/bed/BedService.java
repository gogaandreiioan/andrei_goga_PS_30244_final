package sample.service.bed;

import org.springframework.data.repository.query.Param;
import sample.dto.BedDto;
import sample.dto.SaloonDto;
import sample.entity.Bed;
import sample.entity.Saloon;

import java.util.List;

public interface BedService {

    BedDto findByNumberAndSaloon(int number, SaloonDto saloonDto);
    List<BedDto> findAll();
    List<BedDto> findAllBySaloon(SaloonDto saloonDto);
    List<BedDto> findAllByIsOccupiedIsFalse();
    List<BedDto> findAllByIsOccupiedIsTrue();
    void updateOccupied(@Param("isOccupied") boolean isOccupied, BedDto bedDto);
    void delete(SaloonDto saloonDto);
}

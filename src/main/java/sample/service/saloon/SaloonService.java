package sample.service.saloon;

import sample.dto.SaloonDto;

import java.util.List;

public interface SaloonService {

    void create(SaloonDto saloonDto);
    List<SaloonDto> findAll();
    SaloonDto findByNumber(int number);
    void deleteByNumber(int number);

}

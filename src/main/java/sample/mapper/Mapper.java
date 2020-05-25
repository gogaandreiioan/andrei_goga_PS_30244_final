package sample.mapper;

public interface Mapper<ENTITY, DTO> {

    ENTITY mapTo(DTO DTO);
    DTO mapFrom(ENTITY ENTITY);

}

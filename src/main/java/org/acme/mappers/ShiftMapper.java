package org.acme.mappers;

import org.acme.dtos.ShiftDto;
import org.acme.entity.Shift;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(uses = Shift.class, componentModel = "cdi")
public interface ShiftMapper {

    ShiftMapper INSTANCE = Mappers.getMapper(ShiftMapper.class);

    ShiftDto toDto(Shift shift);

    @Mapping(target = "shiftId", ignore = true)
    Shift toEntity(ShiftDto shiftDto);
}

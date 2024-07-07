package org.acme.mappers;

import org.acme.dtos.NewShiftDto;
import org.acme.dtos.ShiftDto;
import org.acme.entity.Shift;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "cdi")
public interface ShiftMapper {

    @Mapping(source = "medicSpecialist.id", target = "medicSpecialistId")
    ShiftDto toDto(Shift shift);

    @Mapping(target = "id", ignore = true)
    Shift toEntity(ShiftDto shiftDto);
    
    @Mapping(target = "id", ignore = true)
    @Mapping(source = "medicSpecialistId", target = "medicSpecialist.id")
    Shift toEntity(NewShiftDto newShiftDto);
}

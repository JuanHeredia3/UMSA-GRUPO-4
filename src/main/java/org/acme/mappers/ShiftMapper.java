package org.acme.mappers;

import org.acme.dtos.NewShiftDto;
import org.acme.dtos.ShiftDto;
import org.acme.entity.Shift;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "cdi")
public interface ShiftMapper {

    @Mapping(source = "medicSpecialist", target = "medicSpecialist")
    ShiftDto toDto(Shift shift);

    @Mapping(source = "medicSpecialist", target = "medicSpecialist")
    @Mapping(target = "shiftId", ignore = true)
    Shift toEntity(ShiftDto shiftDto);
    
    @Mapping(source = "medicSpecialist", target = "medicSpecialist")
    @Mapping(target = "shiftId", ignore = true)
    Shift toEntity(NewShiftDto newShiftDto);
}

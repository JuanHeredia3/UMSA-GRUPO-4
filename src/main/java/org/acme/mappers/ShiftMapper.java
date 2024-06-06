package org.acme.mappers;

import org.acme.dtos.ShiftDto;
import org.acme.entity.MedicSpecialist;
import org.acme.entity.Shift;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(uses = MedicSpecialist.class, componentModel = "cdi")
public interface ShiftMapper {

	ShiftMapper INSTANCE = Mappers.getMapper(ShiftMapper.class);
	
	@Mapping(source = "medicSpecialist", target = "medicSpecialist")
    ShiftDto toDto(Shift shift);
	
	@Mapping(source = "medicSpecialist", target = "medicSpecialist")
    @Mapping(target = "id", ignore = true)
    Shift toEntity(ShiftDto shiftDto);
	
}

package org.acme.mappers;

import org.acme.dtos.MedicSpecialistDto;
import org.acme.entity.MedicSpecialist;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(uses = ConsultationHoursMapper.class, componentModel = "cdi")
public interface MedicSpecialistMapper {
    
    MedicSpecialistMapper INSTANCE = Mappers.getMapper(MedicSpecialistMapper.class);
    
    @Mapping(source = "consultationHours", target = "consultationHours")
    MedicSpecialistDto toDto(MedicSpecialist medicSpecialist);

    @Mapping(source = "consultationHours", target = "consultationHours")
    @Mapping(target = "id", ignore = true)
    MedicSpecialist toEntity(MedicSpecialistDto medicSpecialistDto);
}

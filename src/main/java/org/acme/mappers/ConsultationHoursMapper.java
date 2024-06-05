package org.acme.mappers;

import org.acme.dtos.ConsultationHoursDto;
import org.acme.entity.ConsultationHours;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "cdi")
public interface ConsultationHoursMapper {

    ConsultationHoursMapper INSTANCE = Mappers.getMapper(ConsultationHoursMapper.class);
    
    ConsultationHoursDto toDto(ConsultationHours consultationHours);

    @Mapping(target = "id", ignore = true)
    ConsultationHours toEntity(ConsultationHoursDto consultationHoursDto);
}

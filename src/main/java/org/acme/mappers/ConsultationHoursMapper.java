package org.acme.mappers;

import org.acme.dtos.ConsultationHoursDto;
import org.acme.dtos.NewConsultationHourDto;
import org.acme.entity.ConsultationHours;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "cdi")
public interface ConsultationHoursMapper {
    
    @Mapping(source = "medicSpecialist", target = "medicSpecialist")
    ConsultationHoursDto toDto(ConsultationHours consultationHours);

    
    @Mapping(source = "medicSpecialist", target = "medicSpecialist")
    @Mapping(target = "id", ignore = true)
    ConsultationHours toEntity(ConsultationHoursDto consultationHoursDto);
    
    @Mapping(source = "medicSpecialist", target = "medicSpecialist")
    @Mapping(target = "id", ignore = true)
    ConsultationHours toEntity(NewConsultationHourDto newConsultationHourDto);
}

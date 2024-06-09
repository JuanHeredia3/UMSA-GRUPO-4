package org.acme.mappers;

import org.acme.dtos.ConsultationHoursDto;
import org.acme.dtos.NewConsultationHourDto;
import org.acme.entity.ConsultationHours;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "cdi")
public interface ConsultationHoursMapper {

    @Mapping(source = "medicSpecialist.id", target = "medicSpecialistId")
    ConsultationHoursDto toDto(ConsultationHours consultationHours);

    @Mapping(target = "id", ignore = true)
    ConsultationHours toEntity(ConsultationHoursDto consultationHoursDto);
    
    @Mapping(target = "id", ignore = true)
    @Mapping(source = "medicSpecialistId", target = "medicSpecialist.id")
    ConsultationHours toEntity(NewConsultationHourDto newConsultationHourDto);
}
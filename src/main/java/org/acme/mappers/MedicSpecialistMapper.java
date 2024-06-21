package org.acme.mappers;

import org.acme.dtos.MedicSpecialistDto;
import org.acme.dtos.NewMedicSpecialistDto;
import org.acme.entity.MedicSpecialist;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "cdi")
public interface MedicSpecialistMapper {
    
    @Mapping(source = "consultationHours", target = "consultationHours")
    @Mapping(source = "medicalSpeciality", target = "medicalSpecialty")
    MedicSpecialistDto toDto(MedicSpecialist medicSpecialist);

    @Mapping(source = "consultationHours", target = "consultationHours")
    @Mapping(source = "medicalSpecialty", target = "medicalSpeciality")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "shift", ignore = true)
    MedicSpecialist toEntity(MedicSpecialistDto medicSpecialistDto);
    
    @Mapping(target = "id", ignore = true)
    MedicSpecialist toEntity(NewMedicSpecialistDto newMedicSpecialist);
}

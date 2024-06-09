package org.acme.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;
import org.acme.dtos.ConsultationHoursDto;
import org.acme.dtos.NewConsultationHourDto;
import org.acme.entity.ConsultationHours;
import org.acme.mappers.ConsultationHoursMapper;
import org.acme.repository.ConsultationHoursRepository;

@ApplicationScoped
public class ConsultationHoursService {
    @Inject
    ConsultationHoursRepository consultationHoursRepository;
    
    @Inject
    ConsultationHoursMapper mapper; 
    
    @Transactional
    public List<ConsultationHoursDto> getAll(){
        List<ConsultationHours> consultationHoursList = consultationHoursRepository.listAll();
        return consultationHoursList.stream()
                    .map(ConsultationHours -> mapper.toDto(ConsultationHours))
                    .collect(Collectors.toList());
    }
    
    @Transactional
    public ConsultationHoursDto create(NewConsultationHourDto newConsultationHourDto) {
        ConsultationHours consultationHours = mapper.toEntity(newConsultationHourDto);
        consultationHours.persist();
        return mapper.toDto(consultationHours);
    }
}

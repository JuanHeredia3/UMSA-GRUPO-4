package org.acme.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;
import org.acme.dtos.ConsultationHoursDto;
import org.acme.dtos.NewConsultationHourDto;
import org.acme.entity.ConsultationHours;
import org.acme.entity.MedicSpecialist;
import org.acme.mappers.ConsultationHoursMapper;
import org.acme.repository.ConsultationHoursRepository;
import org.acme.repository.MedicSpecialistRepository;

@ApplicationScoped
public class ConsultationHoursService {
    @Inject
    ConsultationHoursRepository consultationHoursRepository;
    
    @Inject
    MedicSpecialistRepository medicSpecialistRepository;
    
    @Inject
    ConsultationHoursMapper mapper; 
    
@Transactional
public List<ConsultationHoursDto> getAll(){
    List<Object> mixedList = consultationHoursRepository.listAll(); // Assuming this list might contain different types
    return mixedList.stream()
                    .filter(ConsultationHours.class::isInstance) // Ensure only ConsultationHours instances are processed
                    .map(ConsultationHours.class::cast)
                    .map(consultationHours -> mapper.toDto(consultationHours))
                    .collect(Collectors.toList());
}
    
    @Transactional
    public ConsultationHoursDto create(NewConsultationHourDto newConsultationHourDto) {
        MedicSpecialist medicSpecialist = medicSpecialistRepository.findById(newConsultationHourDto.medicSpecialistId);
        
        if (medicSpecialist == null) {
            throw new IllegalArgumentException("Medical specialist not found");
        }
        
        ConsultationHours consultationHours = mapper.toEntity(newConsultationHourDto);
        consultationHours.medicSpecialist = medicSpecialist;
        
        consultationHoursRepository.persist(consultationHours);
        
        return mapper.toDto(consultationHours);
    }
}

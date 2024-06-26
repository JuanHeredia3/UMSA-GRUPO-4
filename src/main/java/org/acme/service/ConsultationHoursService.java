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
import org.acme.exceptions.BusinessRuleException;
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
    public List<ConsultationHoursDto> getAll() {
        return consultationHoursRepository.listAll().stream()
                .map(consultationHours -> mapper.toDto(consultationHours))
                .collect(Collectors.toList());
    }
    
    public  List<ConsultationHoursDto> getByMedicSpecialistId(Long id){
        return consultationHoursRepository.getByMedicSpecialistId(id).stream()
                .map(ConsultationHours -> mapper.toDto(ConsultationHours))
                .collect(Collectors.toList());
    }

    @Transactional
    public ConsultationHoursDto create(NewConsultationHourDto newConsultationHourDto) throws BusinessRuleException {
        MedicSpecialist medicSpecialist = medicSpecialistRepository.findById(newConsultationHourDto.medicSpecialistId);

        if (medicSpecialist == null) {
            throw new BusinessRuleException("Medical specialist not found");
        }   

        ConsultationHours consultationHours = mapper.toEntity(newConsultationHourDto);
        consultationHours.medicSpecialist = medicSpecialist;

        consultationHoursRepository.persist(consultationHours);

        return mapper.toDto(consultationHours);
    }
    
    @Transactional
    public boolean delete(Long id) {
        return consultationHoursRepository.deleteById(id);
    }
}

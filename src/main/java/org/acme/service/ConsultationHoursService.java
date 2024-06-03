package org.acme.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import java.util.List;
import org.acme.entity.ConsultationHours;
import org.acme.repository.ConsultationHoursRepository;

@ApplicationScoped
public class ConsultationHoursService {
    @Inject
    ConsultationHoursRepository consultationHoursRepository;
    
    @Transactional
    public List<ConsultationHours> getAll(){
      return consultationHoursRepository.listAll();
    }
}

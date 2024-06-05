package org.acme.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;
import org.acme.dtos.MedicSpecialistDto;
import org.acme.mappers.MedicSpecialistMapper;
import org.acme.repository.MedicSpecialistRepository;

@ApplicationScoped
public class MedicSpecialistService {
    @Inject
    MedicSpecialistRepository medicSpecialistRepository;
    
    @Inject
    MedicSpecialistMapper mapper;
    
    @Transactional
    public List<MedicSpecialistDto> getAll(){
      return medicSpecialistRepository.listAll().stream()
              .map(MedicSpecialist -> mapper.toDto(MedicSpecialist))
                    .collect(Collectors.toList());
    }
}

package org.acme.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import java.util.List;
import org.acme.entity.MedicSpecialist;
import org.acme.repository.MedicSpecialistRepository;

@ApplicationScoped
public class MedicSpecialistService {
    @Inject
    MedicSpecialistRepository medicSpecialistRepository;
    
    @Transactional
    public List<MedicSpecialist> getAll(){
      return medicSpecialistRepository.listAll();
    }
}

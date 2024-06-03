package org.acme.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.acme.entity.MedicSpecialist;

@ApplicationScoped
public class MedicSpecialistRepository implements PanacheRepository<MedicSpecialist>{
    
}

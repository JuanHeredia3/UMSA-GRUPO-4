package org.acme.repository;

import org.acme.entity.Shift;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class ShiftRepository implements PanacheRepository<Shift>{
    
    public List<Shift> getByMedicSpecialistId(Long medicSpecialistId) {
        return list("medicSpecialist.id", medicSpecialistId);
    }
}

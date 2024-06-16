package org.acme.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import java.util.List;
import org.acme.entity.ConsultationHours;

@ApplicationScoped
public class ConsultationHoursRepository implements PanacheRepository<ConsultationHours> {
    
    public List<ConsultationHours> getByMedicSpecialistId(Long medicSpecialistId) {
        return list("medicSpecialist.id", medicSpecialistId);
    }
}

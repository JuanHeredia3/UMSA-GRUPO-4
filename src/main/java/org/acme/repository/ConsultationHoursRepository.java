package org.acme.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.acme.entity.ConsultationHours;

@ApplicationScoped
public class ConsultationHoursRepository implements PanacheRepository<ConsultationHours> {
    
}

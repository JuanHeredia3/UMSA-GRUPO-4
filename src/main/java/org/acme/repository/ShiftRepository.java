package org.acme.repository;

import org.acme.entity.Shift;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ShiftRepository implements PanacheRepository<Shift>{

	
}

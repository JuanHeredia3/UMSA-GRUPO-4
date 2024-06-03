package org.acme.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;

@Entity
@Table(name = "medic_specialist")
public class MedicSpecialist extends PanacheEntity {
    public String name;
    public String medicalSpecialty;
    public String consultationLocation;

    @OneToMany(mappedBy = "medicSpecialist")
    public List<ConsultationHours> consultationHours;
}
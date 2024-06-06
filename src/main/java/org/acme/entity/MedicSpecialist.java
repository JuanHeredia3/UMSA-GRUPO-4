package org.acme.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;


@Entity
@Table(name = "medic_specialist")
public class MedicSpecialist extends PanacheEntity {
    
    @Column(name = "name")
    public String name;
    
    @Column(name = "medical_speciality")
    public String medicalSpeciality;
    
    @Column(name = "consultation_location")
    public String consultationLocation;

    @OneToMany(mappedBy = "medicSpecialist")
    public List<ConsultationHours> consultationHours;
    
    @OneToMany(mappedBy = "medicSpecialist")
    public List<Shift> shift;
}
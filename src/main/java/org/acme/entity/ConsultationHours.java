package org.acme.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.DayOfWeek;
import java.time.LocalTime;

@Entity
@Table(name = "consultation_hours")
public class ConsultationHours extends PanacheEntity {
    public DayOfWeek dayOfWeek;
    public LocalTime startTime;
    public LocalTime endTime;

    @ManyToOne
    public MedicSpecialist medicSpecialist;
}

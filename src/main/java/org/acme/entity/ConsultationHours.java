package org.acme.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalTime;

@Entity
@Table(name = "consultation_hours")
public class ConsultationHours extends PanacheEntity {

    @Column(name = "day_of_week")
    public String dayOfWeek;

    @Column(name = "start_time")
    public LocalTime startTime;

    @Column(name = "end_time")
    public LocalTime endTime;

    @ManyToOne
    public MedicSpecialist medicSpecialist;
}

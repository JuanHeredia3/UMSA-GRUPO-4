package org.acme.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.time.LocalTime;

@Entity
@Table(name = "consultation_hours")
public class ConsultationHours extends PanacheEntity {

    @Column(name = "day_of_week")
    public String dayOfWeek;

    @Column(name = "start_time")
    @Temporal(TemporalType.TIME)
    public LocalTime startTime;

    @Column(name = "end_time")
    @Temporal(TemporalType.TIME)
    public LocalTime endTime;

    @ManyToOne
    @JoinColumn(name = "medic_specialist_id")
    public MedicSpecialist medicSpecialist;
}

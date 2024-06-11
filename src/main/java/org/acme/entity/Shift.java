package org.acme.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.CascadeType;
import java.time.LocalTime;
import java.util.Date;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.util.List;

@Entity
@Table(name = "shifts")
public class Shift extends PanacheEntity {

    @Column(name = "pacient_name")
    public String pacientName;

    @Column(name = "shift_date")
    @Temporal(TemporalType.DATE)
    public Date shiftDate;

    @Column(name = "start_time")
    @Temporal(TemporalType.TIME)
    public LocalTime startTime;

    @Column(name = "end_time")
    @Temporal(TemporalType.TIME)
    public LocalTime endTime;

    @Column(name = "consultation")
    public String consultation;
    
    @Column(name = "state")
    public String state;

    @ManyToOne
    public MedicSpecialist medicSpecialist;

    @OneToMany(mappedBy = "shift", cascade = CascadeType.ALL, orphanRemoval = true)
    public List<Recipe> recipes;
}

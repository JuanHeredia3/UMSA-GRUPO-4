package org.acme.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "recipe")
public class Recipe extends PanacheEntity {

    @Column(name = "date")
    public Date date;
    
    @Column(name = "description")
    public String description;
    
    @ManyToOne
    @JoinColumn(name = "shift_id")
    public Shift shift;
}

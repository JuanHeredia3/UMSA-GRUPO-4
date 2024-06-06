package org.acme.dtos;

import java.util.List;
import org.acme.entity.ConsultationHours;
import org.acme.entity.Shift;

public class MedicSpecialistDto {
    public String name;
    public String medicalSpecialty;
    public String consultationLocation;
    public List<ConsultationHours> consultationHours;
    public List<Shift> shift;
}

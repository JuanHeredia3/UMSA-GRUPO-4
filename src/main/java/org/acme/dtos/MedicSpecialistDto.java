package org.acme.dtos;

import java.util.List;
import org.acme.entity.ConsultationHours;

public class MedicSpecialistDto {
    public String name;
    public String medicalSpecialty;
    public String consultationLocation;
    public List<ConsultationHours> consultationHours;
}

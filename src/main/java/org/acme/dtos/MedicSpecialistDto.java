package org.acme.dtos;

import java.util.List;

public class MedicSpecialistDto {
    public Long id;
    public String name;
    public String medicalSpecialty;
    public String consultationLocation;
    public List<ConsultationHoursDto> consultationHours;
}

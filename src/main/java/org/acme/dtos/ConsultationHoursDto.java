package org.acme.dtos;

import java.time.DayOfWeek;
import java.time.LocalTime;
import org.acme.entity.MedicSpecialist;

public class ConsultationHoursDto {
    public DayOfWeek dayOfWeek;
    public LocalTime startTime;
    public LocalTime endTime;
    public MedicSpecialist medicSpecialist;
}

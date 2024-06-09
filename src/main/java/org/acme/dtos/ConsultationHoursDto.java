package org.acme.dtos;

import java.time.DayOfWeek;
import java.time.LocalTime;

public class ConsultationHoursDto {
    public Long id;
    public DayOfWeek dayOfWeek;
    public LocalTime startTime;
    public LocalTime endTime;
    public Long medicSpecialistId;
}

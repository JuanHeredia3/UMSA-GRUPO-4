package org.acme.dtos;

import java.time.DayOfWeek;
import java.time.LocalTime;

public class NewConsultationHourDto {
    public DayOfWeek dayOfWeek;
    public LocalTime startTime;
    public LocalTime endTime;
    public MedicSpecialistDto medicSpecialist;
}

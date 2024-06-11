package org.acme.dtos;

import java.time.LocalTime;

public class UpdateShiftDto {
    public LocalTime startTime;
    public LocalTime endTime;
    public Long medicSpecialistId;
    public String consultation;
}

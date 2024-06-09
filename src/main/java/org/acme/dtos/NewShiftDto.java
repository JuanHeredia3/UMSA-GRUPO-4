package org.acme.dtos;

import java.time.LocalTime;
import java.util.Date;

public class NewShiftDto {
    public String pacientName;
    public Date shiftDate;
    public LocalTime startTime;
    public LocalTime endTime;
    public String consultation;
    public Long medicSpecialistId;
}

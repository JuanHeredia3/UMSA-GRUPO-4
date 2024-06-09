package org.acme.dtos;

import java.time.LocalTime;
import java.util.Date;
import java.util.List;

public class ShiftDto {
    public Long id;
    public String pacientName;
    public Date shiftDate;
    public LocalTime startTime;
    public LocalTime endTime;
    public String consultation;
    public Long medicSpecialistId;
    public List<RecipeDto> recipes;
}

package org.acme.dtos;

import java.time.LocalTime;
import java.util.Date;
import java.util.List;

public class ShiftDto {

    public String pacientName;
    public Date shiftDate;
    public LocalTime startTime;
    public LocalTime endTime;
    public String consultation;
    public MedicSpecialistDto medicSpecialist;
    public List<RecipeDto> recipes;
}

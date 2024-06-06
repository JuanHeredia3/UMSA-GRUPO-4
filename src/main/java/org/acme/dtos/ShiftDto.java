package org.acme.dtos;

import java.time.LocalTime;
import java.util.Date;

import org.acme.entity.MedicSpecialist;

public class ShiftDto {

	public Long shiftId;
	public String pacientName;
	public Date shiftDate;
	public LocalTime startTime;
	public LocalTime endTime;
	public String consultation;
	public MedicSpecialist medicSpecialist;
	
}

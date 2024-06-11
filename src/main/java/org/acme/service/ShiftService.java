package org.acme.service;

import org.acme.entity.Shift;
import org.acme.mappers.ShiftMapper;
import org.acme.repository.ShiftRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import org.acme.dtos.NewShiftDto;
import org.acme.dtos.ShiftDto;
import org.acme.dtos.UpdateShiftDto;
import org.acme.entity.MedicSpecialist;
import org.acme.repository.MedicSpecialistRepository;

@ApplicationScoped
public class ShiftService {

    @Inject
    ShiftRepository shiftRepository;

    @Inject
    MedicSpecialistRepository medicSpecialistRepository;

    @Inject
    ShiftMapper mapper;

    @Transactional
    public List<ShiftDto> getAll() {
        return shiftRepository.listAll().stream()
                .map(Shift -> mapper.toDto(Shift))
                .collect(Collectors.toList());
    }

    @Transactional
    public ShiftDto create(NewShiftDto newShift) {
        MedicSpecialist medicSpecialist = medicSpecialistRepository.findById(newShift.medicSpecialistId);
        if (medicSpecialist == null) {
            throw new IllegalArgumentException("Invalid medicSpecialistId");
        }

        if (newShift.shiftDate.before(new Date())) {
            throw new IllegalArgumentException("Shift date cannot be in the past");
        }
        
        Shift shift = mapper.toEntity(newShift);
        shift.medicSpecialist = medicSpecialist;
        shift.state = "Created";
        shiftRepository.persist(shift);
        return mapper.toDto(shift);
    }

    @Transactional
    public boolean updateShift(Long id, UpdateShiftDto updateShiftDto) {
        Shift auxShift = shiftRepository.findById(id);

        MedicSpecialist medicSpecialist = null;

        if (updateShiftDto.medicSpecialistId != null) {

            medicSpecialist = medicSpecialistRepository.findById(updateShiftDto.medicSpecialistId);

            if (medicSpecialist == null) {
                throw new IllegalArgumentException("Invalid medicSpecialistId");
            }
        }

        if (auxShift == null) {
            return false;
        } else {
            auxShift.startTime = updateShiftDto.startTime != null ? updateShiftDto.startTime : auxShift.startTime;
            auxShift.endTime = updateShiftDto.endTime != null ? updateShiftDto.endTime : auxShift.endTime;
            auxShift.medicSpecialist = medicSpecialist != null ? medicSpecialist : auxShift.medicSpecialist;
            auxShift.consultation = updateShiftDto.consultation != null ? updateShiftDto.consultation : auxShift.consultation;
            return true;
        }
    }
    
    @Transactional
    public boolean updateShiftState(Long id, String newState) {
        if (!newState.equals("Cancelled") && !newState.equals("Closed")) {
            throw new IllegalArgumentException("State not valid");
        }
        
        Shift auxShift = shiftRepository.findById(id);

        if (auxShift == null) {
            return false;
        } else {
            auxShift.state = newState;
            return true;
        }
    }

    @Transactional
    public boolean deleteShift(Long shiftId) {
        return shiftRepository.deleteById(shiftId);
    }
}

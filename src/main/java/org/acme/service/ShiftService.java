package org.acme.service;

import org.acme.entity.Shift;
import org.acme.mappers.ShiftMapper;
import org.acme.repository.ShiftRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;
import org.acme.dtos.NewShiftDto;
import org.acme.dtos.ShiftDto;
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
    public List<ShiftDto> getAll(){
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
        
        Shift shift = mapper.toEntity(newShift);
        shift.medicSpecialist = medicSpecialist;
        shiftRepository.persist(shift);
        return mapper.toDto(shift);
    }

    @Transactional
    public boolean updateShift(Long id, ShiftDto shiftDto) {

        Shift auxShift = shiftRepository.findById(id);

        if (auxShift == null) {
            return false;
        } else {
            shiftRepository.getEntityManager().merge(shiftDto);
            return true;
        }
    }

    @Transactional
    public boolean deleteShift(Long shiftId) {
        return shiftRepository.deleteById(shiftId);
    }
}

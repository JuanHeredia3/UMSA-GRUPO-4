package org.acme.service;

import org.acme.entity.Shift;
import org.acme.mappers.ShiftMapper;
import org.acme.repository.ShiftRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.acme.dtos.NewShiftDto;
import org.acme.dtos.ShiftDto;

@ApplicationScoped
public class ShiftService {

    @Inject
    ShiftRepository shiftRepository;

    @Inject
    ShiftMapper mapper;

    @Transactional
    public ShiftDto create(NewShiftDto newShift) {
        Shift shift = mapper.toEntity(newShift);
        shiftRepository.persist(shift);
        return mapper.toDto(shift);
    }

    @Transactional
    public boolean updateShift(Long id, Shift shift) {

        Shift auxShift = shiftRepository.findById(id);

        if (auxShift == null) {
            return false;
        } else {
            shiftRepository.getEntityManager().merge(shift);
            return true;
        }
    }

    @Transactional
    public boolean deleteShift(Long shiftId) {
        return shiftRepository.deleteById(shiftId);
    }
}

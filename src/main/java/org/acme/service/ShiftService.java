package org.acme.service;

import org.acme.entity.Shift;
import org.acme.mappers.ShiftMapper;
import org.acme.repository.ShiftRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class ShiftService {

	@Inject
	ShiftRepository shiftRepository;
	
	@Inject
	ShiftMapper shiftMapper;
	
	@Transactional
	public boolean addShift(Shift shift) {
		shiftRepository.persist(shift);
		if (shiftRepository.isPersistent(shift)) {
			return true;
		} else {
			return false;
		}
	}
	
	@Transactional
	public boolean updateShift(Long id,Shift shift) {

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

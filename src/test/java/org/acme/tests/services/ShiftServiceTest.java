package org.acme.tests.services;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;
import org.acme.dtos.NewShiftDto;
import org.acme.dtos.ShiftDto;
import org.acme.entity.MedicSpecialist;
import org.acme.entity.Shift;
import org.acme.exceptions.BusinessRuleException;
import org.acme.mappers.ShiftMapper;
import org.acme.repository.MedicSpecialistRepository;
import org.acme.repository.ShiftRepository;
import org.acme.service.ShiftService;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ShiftServiceTest {
    @Mock
    ShiftMapper mapper;
    
    @Mock
    ShiftRepository shiftRepository;
    
    @Mock
    MedicSpecialistRepository medicSpecialistRepository;
    
    @InjectMocks
    ShiftService shiftService;
    
    @Test
    public void testCreateShift_Success() throws BusinessRuleException{
        NewShiftDto dto = new NewShiftDto();
        dto.pacientName = "Test Pacient";
        dto.consultation = "Test Consultation";
        dto.startTime = LocalTime.of(11, 0);
        dto.endTime = LocalTime.of(12, 0);
        dto.shiftDate = Date.from(LocalDate.now().plusDays(1).atStartOfDay(ZoneId.systemDefault()).toInstant());
        dto.medicSpecialistId = 1L;
        
        when(medicSpecialistRepository.findById(1L)).thenReturn(new MedicSpecialist());
        when(mapper.toEntity(dto)).thenReturn(new Shift());
        when(mapper.toDto(any(Shift.class))).thenReturn(new ShiftDto());
        
        ShiftDto result = shiftService.create(dto);
        
        verify(shiftRepository, times(1)).persist(any(Shift.class));
        assertNotNull(result);
    }
    
    @Test
    public void testCreateShift_MedicSpecialistNotFound() throws BusinessRuleException{
        NewShiftDto dto = new NewShiftDto();
        dto.pacientName = "Test Pacient";
        dto.consultation = "Test Consultation";
        dto.startTime = LocalTime.of(11, 0);
        dto.endTime = LocalTime.of(12, 0);
        dto.shiftDate = Date.from(LocalDate.now().plusDays(1).atStartOfDay(ZoneId.systemDefault()).toInstant());
        dto.medicSpecialistId = 1L;
        
        when(medicSpecialistRepository.findById(1L)).thenReturn(null);
        
        assertThrows(BusinessRuleException.class, () -> {
            shiftService.create(dto);
        });
    }
    
    @Test
    public void testCreateShift_ShiftDateCannotBeInThePast() throws BusinessRuleException{
        NewShiftDto dto = new NewShiftDto();
        dto.pacientName = "Test Pacient";
        dto.consultation = "Test Consultation";
        dto.startTime = LocalTime.of(11, 0);
        dto.endTime = LocalTime.of(12, 0);
        dto.shiftDate = Date.from(LocalDate.now().minusDays(1).atStartOfDay(ZoneId.systemDefault()).toInstant());
        dto.medicSpecialistId = 1L;
        
        when(medicSpecialistRepository.findById(1L)).thenReturn(new MedicSpecialist());
        
        assertThrows(BusinessRuleException.class, () -> {
            shiftService.create(dto);
        });
    }
    
    @Test
    public void testDeleteConsultationHour_Success(){
        when(shiftRepository.deleteById(1L)).thenReturn(true);

        boolean result = shiftService.delete(1L);

        verify(shiftRepository, times(1)).deleteById(1L);
        assertTrue(result);
    }
}

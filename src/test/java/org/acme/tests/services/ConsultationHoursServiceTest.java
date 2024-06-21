package org.acme.tests.services;

import java.time.DayOfWeek;
import org.acme.dtos.ConsultationHoursDto;
import org.acme.dtos.NewConsultationHourDto;
import org.acme.entity.ConsultationHours;
import org.acme.entity.MedicSpecialist;
import org.acme.exceptions.BusinessRuleException;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.time.LocalTime;
import java.util.Optional;
import javax.swing.text.SimpleAttributeSet;
import org.acme.mappers.ConsultationHoursMapper;
import org.acme.repository.ConsultationHoursRepository;
import org.acme.repository.MedicSpecialistRepository;
import org.acme.service.ConsultationHoursService;
import static org.junit.jupiter.api.Assertions.assertTrue; 
import static org.junit.jupiter.api.Assertions.assertNotNull;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
public class ConsultationHoursServiceTest {
    @Mock
    ConsultationHoursRepository consultationHoursRepository;

    @Mock
    MedicSpecialistRepository medicSpecialistRepository;

    @Mock
    ConsultationHoursMapper mapper;

    @InjectMocks
    ConsultationHoursService consultationHoursService;

    @Test
    public void testCreateConsultationHour_Success() throws BusinessRuleException {
        NewConsultationHourDto dto = new NewConsultationHourDto();
        dto.medicSpecialistId = 1L;
        dto.dayOfWeek = DayOfWeek.MONDAY;
        dto.startTime = LocalTime.of(8, 0);
        dto.endTime = LocalTime.of(10, 0);

        when(medicSpecialistRepository.findById(1L)).thenReturn(new MedicSpecialist());
        when(mapper.toEntity(dto)).thenReturn(new ConsultationHours());
        when(mapper.toDto(any(ConsultationHours.class))).thenReturn(new ConsultationHoursDto());

        ConsultationHoursDto result = consultationHoursService.create(dto);

        verify(consultationHoursRepository, times(1)).persist(any(ConsultationHours.class));
        assertNotNull(result);
    }

    @Test
    public void testCreateConsultationHour_MedicSpecialistNotFound() {
        NewConsultationHourDto dto = new NewConsultationHourDto();
        dto.medicSpecialistId = 1L;
        dto.dayOfWeek = DayOfWeek.MONDAY;
        dto.startTime = LocalTime.of(8, 0);
        dto.endTime = LocalTime.of(10, 0);
        
        when(medicSpecialistRepository.findById(1L)).thenReturn(null);
        
        assertThrows(BusinessRuleException.class, () -> {
            consultationHoursService.create(dto);
        });
    }
    
    @Test
    public void testDeleteConsultationHour_Success(){
        when(consultationHoursRepository.deleteById(1L)).thenReturn(true);

        boolean result = consultationHoursService.delete(1L);

        verify(consultationHoursRepository, times(1)).deleteById(1L);
        assertTrue(result);
    }
}

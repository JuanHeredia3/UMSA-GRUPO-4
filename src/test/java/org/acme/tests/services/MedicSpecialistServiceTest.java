package org.acme.tests.services;

import org.acme.dtos.MedicSpecialistDto;
import static org.mockito.Mockito.*;
import org.acme.dtos.NewMedicSpecialistDto;
import org.acme.entity.MedicSpecialist;
import org.acme.exceptions.BusinessRuleException;
import org.acme.mappers.MedicSpecialistMapper;
import org.acme.repository.MedicSpecialistRepository;
import org.acme.service.MedicSpecialistService;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.Mock;

@ExtendWith(MockitoExtension.class)
public class MedicSpecialistServiceTest {

    @Mock
    MedicSpecialistMapper mapper;
    
    @Mock
    MedicSpecialistRepository medicSpecialistRepository;
    
    @InjectMocks
    MedicSpecialistService medicSpecialistService;
    
    @Test
    public void testCreateMedicSpecialist_Success() throws BusinessRuleException{
        NewMedicSpecialistDto dto = new NewMedicSpecialistDto();
        dto.name = "Test Name";
        dto.consultationLocation = "Test Location";
        dto.medicalSpeciality = "Test Speciality";
        
        when(mapper.toEntity(dto)).thenReturn(new MedicSpecialist());
        when(mapper.toDto(any(MedicSpecialist.class))).thenReturn(new MedicSpecialistDto());
        
        MedicSpecialistDto result = medicSpecialistService.create(dto);
        
        verify(medicSpecialistRepository, times(1)).persist(any(MedicSpecialist.class));
        assertNotNull(result);
    }
    
    @Test
    public void testDeleteConsultationHour_Success(){
        when(medicSpecialistRepository.deleteById(1L)).thenReturn(true);

        boolean result = medicSpecialistService.delete(1L);

        verify(medicSpecialistRepository, times(1)).deleteById(1L);
        assertTrue(result);
    }
}

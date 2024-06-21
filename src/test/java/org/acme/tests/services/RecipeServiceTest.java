package org.acme.tests.services;

import java.time.LocalTime;
import org.acme.dtos.NewRecipeDto;
import org.acme.dtos.RecipeDto;
import org.acme.entity.Recipe;
import org.acme.entity.Shift;
import org.acme.exceptions.BusinessRuleException;
import org.acme.mappers.RecipeMapper;
import org.acme.repository.RecipeRepository;
import org.acme.repository.ShiftRepository;
import org.acme.service.RecipeService;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class RecipeServiceTest {
    @Mock
    RecipeMapper mapper;
    
    @Mock
    RecipeRepository RecipeRepository;
    
    @Mock
    ShiftRepository shiftRepository;
    
    @InjectMocks
    RecipeService recipeService;
    
    @Test
    public void testCreateRecipe_Success() throws BusinessRuleException{
        NewRecipeDto dto = new NewRecipeDto();
        dto.description = "Test Description";
        dto.shiftId = 1L;
        
        Shift validShift = new Shift();
        validShift.id = 1L;
        validShift.startTime = LocalTime.of(11, 0);
        validShift.endTime = LocalTime.of(13, 0);
        validShift.pacientName = "Test Pacient";
        validShift.state = "Close";
        validShift.consultation = "Test Consultation";
        
        
        when(shiftRepository.findById(1L)).thenReturn(validShift);
        when(mapper.toEntity(dto)).thenReturn(new Recipe());
        when(mapper.toDto(any(Recipe.class))).thenReturn(new RecipeDto());
        
        RecipeDto result = recipeService.create(dto);
        
        verify(RecipeRepository, times(1)).persist(any(Recipe.class));
        assertNotNull(result);
    }
    
    @Test
    public void testCreateRecipe_ShiftNotFound() throws BusinessRuleException{
        NewRecipeDto dto = new NewRecipeDto();
        dto.description = "Test Description";
        dto.shiftId = 1L;
        
        when(shiftRepository.findById(1L)).thenReturn(null);
        
        assertThrows(BusinessRuleException.class, () -> {
            recipeService.create(dto);
        });
    }
    
    @Test
    public void testCreateRecipe_ShiftIsNotClosed() throws BusinessRuleException{
        NewRecipeDto dto = new NewRecipeDto();
        dto.description = "Test Description";
        dto.shiftId = 1L;
        
        Shift invalidShift = new Shift();
        invalidShift.id = 1L;
        invalidShift.startTime = LocalTime.of(11, 0);
        invalidShift.endTime = LocalTime.of(13, 0);
        invalidShift.pacientName = "Test Pacient";
        invalidShift.state = "Cancelled";
        invalidShift.consultation = "Test Consultation";
        
        when(shiftRepository.findById(1L)).thenReturn(invalidShift);
        
        assertThrows(BusinessRuleException.class, () -> {
            recipeService.create(dto);
        });
    }
    
    @Test
    public void testDeleteConsultationHour_Success(){
        when(RecipeRepository.deleteById(1L)).thenReturn(true);

        boolean result = recipeService.delete(1L);

        verify(RecipeRepository, times(1)).deleteById(1L);
        assertTrue(result);
    }
}

package org.acme.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.Response;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.acme.dtos.NewRecipeDto;
import org.acme.dtos.RecipeDto;
import org.acme.entity.Recipe;
import org.acme.entity.Shift;
import org.acme.exceptions.BusinessRuleException;
import org.acme.mappers.RecipeMapper;
import org.acme.repository.RecipeRepository;
import org.acme.repository.ShiftRepository;

@ApplicationScoped
public class RecipeService {

    @Inject
    RecipeRepository recipeRepository;
    
    @Inject
    ShiftRepository shiftRepository;

    @Inject
    RecipeMapper mapper;

    @Transactional
    public List<RecipeDto> getAll() {
        return recipeRepository.listAll().stream()
                .map(Recipe -> mapper.toDto(Recipe))
                .collect(Collectors.toList());
    }
    
    @Transactional
    public RecipeDto get(Long id) {
        Optional<Recipe> recipeOptional = recipeRepository.findByIdOptional(id);
        return mapper.toDto(recipeOptional.get());
    }

    @Transactional
    public RecipeDto create(NewRecipeDto newRecipe) throws BusinessRuleException {
        Shift shift = shiftRepository.findById(newRecipe.shiftId);
        
        if (shift == null) {
            throw new BusinessRuleException("Shift not found", Response.Status.BAD_REQUEST.getStatusCode());
        }
        
        if (shift.state.equals("Cancelled") || shift.state.equals("Created")) {
            throw new BusinessRuleException("Shift is not closed", Response.Status.BAD_REQUEST.getStatusCode());
        }

        Recipe recipe = mapper.toEntity(newRecipe);
        recipe.date = new Date();
        recipe.shift = shift;
        
        recipeRepository.persist(recipe);
        
        return mapper.toDto(recipe);
    }

    @Transactional
    public boolean deleteRecipe(Long id) {
        return recipeRepository.deleteById(id);
    }
}

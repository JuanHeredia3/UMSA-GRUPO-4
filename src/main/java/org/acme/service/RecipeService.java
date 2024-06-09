package org.acme.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.acme.dtos.NewRecipeDto;
import org.acme.dtos.RecipeDto;
import org.acme.entity.Recipe;
import org.acme.entity.Shift;
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
        if (recipeOptional.isPresent()) {
            return mapper.toDto(recipeOptional.get());
        } else {
            throw new RuntimeException("Recipe not found");
        }
    }

    @Transactional
    public RecipeDto create(NewRecipeDto newRecipe) {
        Shift shift = shiftRepository.findById(newRecipe.shiftId);
        
        if (shift == null) {
            throw new IllegalArgumentException("Shift not found");
        }

        Recipe recipe = mapper.toEntity(newRecipe);
        recipe.shift = shift;
        
        recipeRepository.persist(recipe);
        
        return mapper.toDto(recipe);
    }

    @Transactional
    public boolean deleteRecipe(Long id) {
        return recipeRepository.deleteById(id);
    }
}
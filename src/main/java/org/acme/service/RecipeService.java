package org.acme.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.acme.dtos.RecipeDto;
import org.acme.entity.Recipe;
import org.acme.mappers.RecipeMapper;
import org.acme.repository.RecipeRepository;

@ApplicationScoped
public class RecipeService {

    @Inject
    RecipeRepository recipeRepository;

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
    public boolean deleteRecipe(Long id) {
        return recipeRepository.deleteById(id);
    }
}

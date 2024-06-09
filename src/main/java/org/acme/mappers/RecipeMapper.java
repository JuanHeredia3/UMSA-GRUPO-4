package org.acme.mappers;

import org.acme.dtos.NewRecipeDto;
import org.acme.dtos.RecipeDto;
import org.acme.entity.Recipe;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "cdi")
public interface RecipeMapper {

    RecipeDto toDto(Recipe recipe);

    @Mapping(target = "id", ignore = true)
    Recipe toEntity(RecipeDto recipeDto);
    
    @Mapping(target = "shift", ignore = true)
    Recipe toEntity(NewRecipeDto newRecipeDto);
}

package org.acme.mappers;

import org.acme.dtos.NewRecipeDto;
import org.acme.dtos.RecipeDto;
import org.acme.entity.Recipe;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "cdi")
public interface RecipeMapper {

    @Mappings({
        @Mapping(source = "shift.id", target = "shiftId") // Mapeo del ID del turno
    })
    RecipeDto toDto(Recipe recipe);

    @Mapping(target = "id", ignore = true)
    Recipe toEntity(RecipeDto recipeDto);

    @Mapping(target = "shift", ignore = true)
    Recipe toEntity(NewRecipeDto newRecipeDto);
}

package org.acme.mappers;

import org.acme.dtos.RecipeDto;
import org.acme.entity.Recipe;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(uses = Recipe.class, componentModel = "cdi")
public interface RecipeMapper {
    RecipeMapper INSTANCE = Mappers.getMapper(RecipeMapper.class);

    RecipeDto toDto(Recipe recipe);

    @Mapping(target = "id", ignore = true)
    Recipe toEntity(RecipeDto recipeDto);
}

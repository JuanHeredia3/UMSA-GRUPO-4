package org.acme.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.acme.entity.Recipe;

@ApplicationScoped
public class RecipeRepository implements PanacheRepository<Recipe>{
    
}

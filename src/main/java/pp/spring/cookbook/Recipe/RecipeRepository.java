package pp.spring.cookbook.Recipe;

import org.springframework.data.jpa.repository.JpaRepository;
import pp.spring.cookbook.Category.Category;

import java.util.List;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {

    List<Recipe> findAllByFavoriteIsTrue();

    List<Recipe> findByCategory(Category category);

}
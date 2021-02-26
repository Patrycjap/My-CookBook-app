package pp.spring.cookbook.Recipe;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pp.spring.cookbook.Category.Category;

import javax.transaction.Transactional;
import java.util.List;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {

    List<Recipe> findByCategory(Category category);

    List<Recipe> findTop2ByOrderByCountLikeDesc();

    @Query("UPDATE Recipe recipe set recipe.countLike = recipe.countLike +1 WHERE recipe.id= :id")
    @Transactional
    @Modifying
    void addLike(@Param("id") Long id);
}
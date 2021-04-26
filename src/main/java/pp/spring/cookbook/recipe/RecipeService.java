package pp.spring.cookbook.recipe;

import org.springframework.stereotype.Service;
import pp.spring.cookbook.category.Category;

import java.util.List;
import java.util.Optional;

@Service
public class RecipeService {

    private final RecipeRepository recipeRepository;

    public RecipeService(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    public List<Recipe> findTop2ByOrderByCountLikeDesc() {
        return recipeRepository.findTop2ByOrderByCountLikeDesc();
    }

    public List<Recipe> findAllRecipes() {
        return recipeRepository.findAll();
    }

    public List<Recipe> findByCategory(Category category) {
        return recipeRepository.findByCategory(category);
    }

    public Optional<Recipe> findById(Long id) {
        return recipeRepository.findById(id);
    }

    public void addLike(Long id) {
        recipeRepository.addLike(id);
    }

    public void editRecipeService(Recipe recipe, Long id) {
        Recipe recipeToEdit = recipeRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Recipe with id " + id + " not found"));
        recipeToEdit.setTitle(recipe.getTitle());
        recipeToEdit.setDescription(recipe.getDescription());
        recipeToEdit.setPortions(recipe.getPortions());
        recipeToEdit.setCategory(recipe.getCategory());
        recipeToEdit.setPreparationTime(recipe.getPreparationTime());
    }

    public void save(Recipe recipeToEdit) {
        recipeRepository.save(recipeToEdit);
    }

    public void deleteById(Long id) {
        recipeRepository.deleteById(id);
    }

    public Object findAll() {
        return recipeRepository.findAll();
    }
}
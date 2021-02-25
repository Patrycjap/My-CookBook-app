package pp.spring.cookbook.Recipe;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pp.spring.cookbook.Category.Category;

import java.util.List;
import java.util.Optional;

@Controller
public class RecipeController {
    private RecipeRepository recipeRepository;

    public RecipeController(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }


    @GetMapping("/recipes")
    public String recipes(Model model, @RequestParam(required = false) Category category) {
        List<Recipe> recipes;
        if (category != null) {
            recipes = recipeRepository.findByCategory(category);
        } else {
            recipes = recipeRepository.findAll();
        }

        model.addAttribute("recipes", recipes);
        return "/recipes";
    }

    @GetMapping("/recipe/{id}")
    public String showRecipe(@PathVariable Long id, Model model) {
        Optional<Recipe> todoOptional = recipeRepository.findById(id);

        if (todoOptional.isPresent()) {
            Recipe recipe = todoOptional.get();
            model.addAttribute("recipe", recipe);
            return "recipe";
        } else {
            return "redirect:/";
        }
    }

    @GetMapping("/recipe/{id}/edit")
    public String recipeEditForm(@PathVariable Long id, Model model) {
        Optional<Recipe> todoOptional = recipeRepository.findById(id);
        if (todoOptional.isPresent()) {
            Recipe recipe = todoOptional.get();
            model.addAttribute("recipeEdit", recipe);
            return "recipeEdit";
        } else {
            return "redirect:/";
        }
    }

    @PostMapping("/recipe/{id}/edit")
    public String editRecipe(@PathVariable Long id, Recipe recipe) {
        Recipe recipeToEdit = recipeRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Id not found"));
        recipeToEdit.setTitle(recipe.getTitle());
        recipeToEdit.setDescription(recipe.getDescription());
        recipeToEdit.setPortions(recipe.getPortions());
        recipeToEdit.setCategory(recipe.getCategory());
        recipeToEdit.setPreparationTime(recipe.getPreparationTime());
        recipeRepository.save(recipeToEdit);
        return "redirect:/recipe/" + recipeToEdit.getId();
    }

    @GetMapping("/recipe/add")
    public String recipeAddForm(Model model) {
        model.addAttribute("recipeAdd", new Recipe());
        return "recipeAdd";
    }

    @PostMapping("/recipe/add")
    public String addRecipe(Recipe recipe) {
        recipeRepository.save(recipe);
        return "redirect:/recipes";
    }

    @GetMapping("/recipe/{id}/delete")
    public String delete(@PathVariable Long id) {
        recipeRepository.deleteById(id);
        return "redirect:/";
    }
}
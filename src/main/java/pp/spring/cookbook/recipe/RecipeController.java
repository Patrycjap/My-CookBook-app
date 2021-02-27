package pp.spring.cookbook.recipe;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pp.spring.cookbook.category.Category;

import java.util.List;
import java.util.Optional;

@Controller
public class RecipeController {

    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping("/recipes")
    public String recipes(Model model, @RequestParam(required = false) Category category) {
        List<Recipe> recipes;
        if (category != null) {
            recipes = recipeService.findByCategory(category);
        } else {
            recipes = recipeService.findAllRecipes();
        }

        model.addAttribute("recipes", recipes);
        return "/recipes";
    }

    @GetMapping("/recipe/{id}")
    public String showRecipe(@PathVariable Long id, Model model) {
        Optional<Recipe> recipeShowOptional = recipeService.findById(id);

        if (recipeShowOptional.isPresent()) {
            Recipe recipe = recipeShowOptional.get();
            model.addAttribute("recipe", recipe);
            return "recipe";
        } else {
            return "redirect:/";
        }
    }

    @GetMapping("/recipe/{id}/edit")
    public String recipeEditForm(@PathVariable Long id, Model model) {
        Optional<Recipe> recipeEditOptional = recipeService.findById(id);
        if (recipeEditOptional.isPresent()) {
            Recipe recipe = recipeEditOptional.get();
            model.addAttribute("recipeEdit", recipe);
            return "recipeEdit";
        } else {
            return "redirect:/";
        }
    }

    @PostMapping("/recipe/{id}/edit")
    public String editRecipe(@PathVariable Long id, Recipe recipe) {
        Recipe recipeToEdit = recipeService.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Recipe with id " + id + " not found"));
        recipeService.editRecipeService(recipe, id);
        recipeService.save(recipeToEdit);
        return "redirect:/recipe/" + recipeToEdit.getId();
    }

    @GetMapping("/recipe/add")
    public String recipeAddForm(Model model) {
        model.addAttribute("recipeAdd", new Recipe());
        return "recipeAdd";
    }

    @PostMapping("/recipe/add")
    public String addRecipe(Recipe recipe) {
        recipeService.save(recipe);
        return "redirect:/recipes";
    }

    @GetMapping("/recipe/{id}/delete")
    public String delete(@PathVariable Long id) {
        recipeService.deleteById(id);
        return "redirect:/";
    }

    @GetMapping("/recipe/{id}/like")
    public String addLike(@PathVariable Long id) {
        recipeService.addLike(id);
        return "redirect:/";
    }
}
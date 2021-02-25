package pp.spring.cookbook.Ingredient;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pp.spring.cookbook.Recipe.RecipeRepository;

@Controller
public class IngredientController {

    IngredientRepository ingredientRepository;
    RecipeRepository recipeRepository;

    public IngredientController(IngredientRepository ingredientRepository, RecipeRepository recipeRepository) {
        this.ingredientRepository = ingredientRepository;
        this.recipeRepository = recipeRepository;
    }

    @GetMapping("/ingredient/add")
    public String addForm(Model model, @RequestParam(required = false, defaultValue = "1") Long recipeId) {
        Ingredient ingredient = new Ingredient();
        ingredient.setRecipe(recipeRepository.findById(recipeId).orElse(null));
        model.addAttribute("ingredientAdd", ingredient);
        model.addAttribute("recipes", recipeRepository.findAll());
        return "ingredientAdd";
    }

    @PostMapping("/ingredient/add")
    public String addForm(Ingredient ingredient) {
        ingredientRepository.save(ingredient);
        return "redirect:/recipe/" + ingredient.getRecipe().getId();
    }
}
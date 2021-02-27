package pp.spring.cookbook.ingredient;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pp.spring.cookbook.recipe.RecipeService;

@Controller
public class IngredientController {

    private final IngredientRepository ingredientRepository;
//    private final RecipeRepository recipeRepository;
    private final RecipeService recipeService;

    public IngredientController(IngredientRepository ingredientRepository, RecipeService recipeService) {
        this.ingredientRepository = ingredientRepository;
        this.recipeService = recipeService;
    }

    @GetMapping("/ingredient/add")
    public String addForm(Model model, @RequestParam(required = false) Long recipeId) {
        Ingredient ingredient = new Ingredient();
        ingredient.setRecipe(recipeService.findById(recipeId)
            .orElseThrow(() -> new IllegalArgumentException("Recipe with id " + recipeId + " not found")));
        model.addAttribute("ingredientAdd", ingredient);
        model.addAttribute("recipes", recipeService.findAll());
        return "ingredientAdd";
    }

    @PostMapping("/ingredient/add")
    public String addForm(Ingredient ingredient) {
        ingredientRepository.save(ingredient);
        return "redirect:/recipe/" + ingredient.getRecipe().getId();
    }
}
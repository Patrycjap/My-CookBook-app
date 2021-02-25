package pp.spring.cookbook;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pp.spring.cookbook.Recipe.RecipeRepository;

@Controller
public class HomeController {

    RecipeRepository recipeRepository;

    public HomeController(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("bestRecipes", recipeRepository.findAllByFavoriteIsTrue());
        return "home";
    }
}
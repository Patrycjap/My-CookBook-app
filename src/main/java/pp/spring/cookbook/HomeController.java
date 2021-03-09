package pp.spring.cookbook;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pp.spring.cookbook.recipe.RecipeService;
import pp.spring.cookbook.user.UserService;

@Controller
public class HomeController {

    private final RecipeService recipeService;
    private final UserService userService;

    public HomeController(RecipeService recipeService, UserService userService) {
        this.recipeService = recipeService;
        this.userService = userService;
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("bestRecipes", recipeService.findTop2ByOrderByCountLikeDesc());
        model.addAttribute("user", userService.findCurrentUser());
        return "home";
    }
}
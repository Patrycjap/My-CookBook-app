package pp.spring.cookbook.user;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthenticationController {

    private UserService userService;

    public AuthenticationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String loginForm(@RequestParam(required = false) String error, Model model) {
        boolean showErrorMessage = false;
        boolean showRegisterMessage = false;

        if (error != null) {
            showErrorMessage = true;
        } else {
            showRegisterMessage = true;
        }

        model.addAttribute("showErrorMessage", showErrorMessage);
        model.addAttribute("showRegisterMessage", showRegisterMessage);

        return "login";
    }

    @GetMapping("/register")
    public String registerForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String register(User user) {
        String username = user.getEmail();
        String rawPassword = user.getPassword();
        userService.registerUser(username, rawPassword);
        return "redirect:/login";
    }

    @GetMapping("/success")
    public String successForm(Model model) {
        model.addAttribute("user", new User());
        return "success";
    }
}
package pp.spring.cookbook.user;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
public class AuthenticationController {

    private UserService userService;

    public AuthenticationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String loginForm(@RequestParam(required = false) String error, Model model) {
        boolean showErrorMessage = false;

        if (error != null) {
            showErrorMessage = true;
        }
        model.addAttribute("showErrorMessage", showErrorMessage);
        model.addAttribute("showSuccessMessage", false);
        return "login";
    }

    @GetMapping("/register")
    public String registerForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String register(Model model, @Valid @ModelAttribute User user,
                           BindingResult bindResult) {
        if (bindResult.hasErrors()) {
            model.addAttribute("showErrorMessage", true);
            return "register";
        } else {
            String firstName = user.getFirstName();
            String lastName = user.getLastName();
            String email = user.getEmail();
            String rawPassword = user.getPassword();
            userService.registerUser(firstName, lastName, email, rawPassword);
            model.addAttribute("showErrorMessage", false);
            model.addAttribute("showSuccessMessage", true);
            return "redirect:/login";
        }
    }

    @GetMapping("/success")
    public String successForm(Model model) {
        model.addAttribute("user", new User());
        return "success";
    }

    @GetMapping("/reset")
    public String resetForm() {
        return "resetForm";
    }

    @PostMapping("/reset")
    public String resetPasswordLinkSend(@RequestParam String email) {
        userService.sendPasswordResetLink(email);
        return "resetFormSend";
    }

    @PostMapping("/resetEnding")
    public String resetPasswordLinkSend(@RequestParam String key, @RequestParam String password) {
        userService.updateUserPassword(key, password);
        return "redirect:/login";
    }

    @GetMapping("/user")
    public String userPanel(Model model) {
        List<User> user = userService.findCurrentUser();
        model.addAttribute("user", user);
        return "/user";
    }

    @GetMapping("/user/{id}/edit")
    public String userEditForm(@PathVariable Long id, Model model) {
        Optional<User> userEditOptional = userService.findById(id);
        if (userEditOptional.isPresent()) {
            User user = userEditOptional.get();
            model.addAttribute("userEdit", user);
            return "userEdit";
        } else {
            return "redirect:/";
        }
    }

    @PostMapping("/user/{id}/edit")
    public String editUser(@PathVariable Long id, User user) {
        User userToEdit = userService.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("User with id " + id + " not found"));
        userService.saveEditedUser(user, id);
        userService.save(userToEdit);
        return "redirect:/user";
    }
}
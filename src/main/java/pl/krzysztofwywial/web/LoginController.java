package pl.krzysztofwywial.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
    @GetMapping("/logowanie")
    String loginForm() {
        return "login-form";
    }

    @GetMapping("/logout")
    String logOut() {
        return "redirect:/";
    }

    @GetMapping("/farewell")
    String farewellForm() {
        return "redirect:/";
    }
}

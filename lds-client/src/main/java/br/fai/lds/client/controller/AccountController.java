package br.fai.lds.client.controller;


import br.fai.lds.client.service.UserService;
import br.fai.lds.model.entities.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/account")
public class AccountController {

    @Autowired
    UserService userService;

    @GetMapping("/sign-up")
    public String getSignUpPage() {
        return "account/sign-up";
    }

    @GetMapping("/sign-in")
    public String getSignInPage() {
        return "account/sign-in";
    }

    @GetMapping("/password-recovery")
    public String getPasswordRecoveryPage() {
        return "account/password-recovery";
    }

    @PostMapping("/create")
    public String create(UserModel user) {

        return "redirect:account/sign-in";
    }

    @PostMapping("/login")
    public String login(@RequestParam("username") final String username, @RequestParam("password") final String password) {

        userService.validateUserNameAndPassword(username, password);

        return "redirect:/account/sign-up";
    }

}
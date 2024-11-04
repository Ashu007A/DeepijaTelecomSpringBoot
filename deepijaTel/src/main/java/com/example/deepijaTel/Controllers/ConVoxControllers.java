package com.example.deepijaTel.Controllers;

import com.example.deepijaTel.Models.ConVoxLogin;
import com.example.deepijaTel.Services.ConVoxServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/convox")
@SessionAttributes("username")
public class ConVoxControllers {

    private static final Logger logger = LoggerFactory.getLogger(ConVoxControllers.class);

    @Autowired
    private ConVoxServices convoxServices;

    @GetMapping("/login")
    public String showAdminLoginForm(Model model) {
        model.addAttribute("page", "convox_login");
        return "convox_login";
    }

    @PostMapping("/login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        Model model) {
        logger.info("Attempting to log in with username: {}", username);
        ConVoxLogin user = convoxServices.findByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            logger.info("Login successful for username: {}", username);
            model.addAttribute("username", username);
            return "redirect:/convox/dashboard";
        } else {
            logger.warn("Login failed for username: {}", username);
            model.addAttribute("error", "Invalid username or password! ");
            return "convox_login";
        }
    }

    @PostMapping("/register")
    public String register(@RequestParam("username") String username,
                           @RequestParam("password") String password,
                           Model model) {
        logger.info("Registering new user with username: {}", username);
        ConVoxLogin newUser = new ConVoxLogin();
        newUser.setUsername(username);
        newUser.setPassword(password);
        convoxServices.saveUser(newUser);
        return "redirect:/convox/login";
    }

    @GetMapping("/dashboard")
    public String showDashboard(Model model, HttpSession session) {
        String username = (String) session.getAttribute("username");
        if (username == null) {
            return "redirect:/convox/login";
        }
        model.addAttribute("username", username);
        return "convox_dashboard";
    }

    @GetMapping("/logout")
    public String logout(SessionStatus sessionStatus) {
        sessionStatus.setComplete();
        return "redirect:/convox/login";
    }
}
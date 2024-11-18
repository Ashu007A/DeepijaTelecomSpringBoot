package com.example.deepijaTel.Controllers.ConVox_Controllers;

import com.example.deepijaTel.Models.Secondary.ConVoxLogin;
import com.example.deepijaTel.Services.Secondary.ConVoxServices;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

@Controller
@RequestMapping("/convox")
@SessionAttributes("username")
public class Dashboard {

    private static final Logger logger = LoggerFactory.getLogger(Dashboard.class);

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

        if (user != null) {
            logger.info("Retrieved user: {} with password: {}", user.getUsername(), user.getPassword());
            if (user.getPassword().equals(password)) {
                logger.info("Login successful for username: {}", username);
                model.addAttribute("username", username);
                return "redirect:/convox/dashboard";
            }
        } else {
            logger.warn("No user found for username: {}", username);
        }
        logger.warn("Login failed for username: {}", username);
        model.addAttribute("error", "Invalid username or password!");
        return "convox_login";
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
        model.addAttribute("page", "convox_dashboard");
        return "ConVox/dashboard";
    }

    @GetMapping("/logout")
    public String logout(SessionStatus sessionStatus) {
        sessionStatus.setComplete();
        return "redirect:/convox/login";
    }
}
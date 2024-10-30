package com.example.deepijaTel.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AdminLoginController {

    @GetMapping("/admin_login")
    public String showAdminLoginForm(Model model) {
        model.addAttribute("page", "admin_login");
        return "admin_login";  // Refers to admin_login.html in the templates folder
    }

    @PostMapping("/admin_login")
    public String processAdminLogin(@RequestParam String username, @RequestParam String password, Model model) {
        // Add your authentication logic here
        if (username.equals("admin") && password.equals("password")) {
            return "redirect:/admin_dashboard";  // Redirect to admin dashboard after successful login
        } else {
            model.addAttribute("error", "Invalid username or password");
            return "admin_login";
        }
    }
}
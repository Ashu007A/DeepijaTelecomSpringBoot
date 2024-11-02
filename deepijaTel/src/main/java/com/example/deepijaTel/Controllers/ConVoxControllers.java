package com.example.deepijaTel.Controllers;

import com.example.deepijaTel.Models.Admin;
import com.example.deepijaTel.Models.User;
import com.example.deepijaTel.Services.AdminServices;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/convox")
public class ConVoxControllers {

    @Autowired
    private AdminServices adminServices;

    @GetMapping("/login")
    public String showAdminLoginForm(Model model) {
        model.addAttribute("page", "index");
        return "index";  // Refers to admin_login_old.html in the templates folder
    }

    @PostMapping("/login")
    public String processAdminLogin(@RequestParam String username, @RequestParam String password, HttpSession session, Model model) {
        String result = adminServices.authenticateAdmin(username, password);
        if (result.equals("Login successful!")) {
            session.setAttribute("admin_username", username);
            Admin admin = adminServices.getAdminByUsername(username);
            session.setAttribute("admin_id", admin.getId());
            return "redirect:/admin/dashboard";  // Redirect to admin dashboard
        } else {
            model.addAttribute("error", result);
            return "admin_login";  // Redirect back to login page with error
        }
    }

    @GetMapping("/dashboard")
    public String showAdminDashboard(Model model, HttpSession session) {
        String adminUsername = (String) session.getAttribute("admin_username");
        if (adminUsername == null) {
            return "redirect:/admin/login";
        }
        List<User> users = adminServices.getAllUsers();
        model.addAttribute("users", users);
        model.addAttribute("page", "admin_dashboard");
        return "admin_dashboard";  // Refers to admin_dashboard.html in the templates folder
    }
}

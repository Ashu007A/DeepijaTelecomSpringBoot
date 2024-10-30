package com.example.deepijaTel.Controllers;

import com.example.deepijaTel.Models.Admin;
import com.example.deepijaTel.Models.User;
import com.example.deepijaTel.Services.AdminServices;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminLoginController {

    @Autowired
    private AdminServices adminServices;

    @GetMapping("/login")
    public String showAdminLoginForm(Model model) {
        model.addAttribute("page", "admin_login");
        return "admin_login";  // Refers to admin_login.html in the templates folder
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
    public String showAdminDashboard(Model model) {
        List<User> users = adminServices.getAllUsers();
        model.addAttribute("users", users);
        return "admin_dashboard";  // Refers to admin_dashboard.html in the templates folder
    }
}
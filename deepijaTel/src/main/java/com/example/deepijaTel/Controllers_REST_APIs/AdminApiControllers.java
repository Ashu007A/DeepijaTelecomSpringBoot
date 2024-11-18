package com.example.deepijaTel.Controllers_REST_APIs;

import com.example.deepijaTel.Models.Primary.Admin;
import com.example.deepijaTel.Models.Primary.User;
import com.example.deepijaTel.Services.Primary.AdminServices;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminApiControllers {

    @Autowired
    private AdminServices adminServices;

    @PostMapping("/login")
    @ResponseBody
    public String loginAdmin(@RequestParam String username, @RequestParam String password, HttpSession session) {
        String result = adminServices.authenticateAdmin(username, password);
        if (result.equals("Login successful!")) {
            session.setAttribute("admin_username", username);
            Admin admin = adminServices.getAdminByUsername(username);
            session.setAttribute("admin_id", admin.getId());
            return "Login successful! Session ID: " + session.getId();
        } else {
            return "Login failed: " + result;
        }
    }
    // http://localhost:8080/api/admin/login

    @GetMapping("/users")
    @ResponseBody
    public List<User> getAllUsers() {
        return adminServices.getAllUsers();
    }
    // http://localhost:8080/api/admin/users
}

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
@RequestMapping("/admin")
public class AdminControllers {

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

    @GetMapping("/add_user")
    public String showAddUserForm(Model model) {
        model.addAttribute("page", "add_user");
        return "admin_add_user";  // Refers to admin_add_user.html in the templates folder
    }

    @PostMapping("/process_add_user")
    public String processAddUser(@RequestParam("name") String name,
                                 @RequestParam("username") String username,
                                 @RequestParam("email") String email,
                                 @RequestParam("password") String password,
                                 @RequestParam("repassword") String repassword,
                                 @RequestParam("phone") String phone,
                                 @RequestParam("state") String state,
                                 @RequestParam("district") String district,
                                 @RequestParam("city") String city,
                                 @RequestParam("dob") String dob,
                                 @RequestParam("gender") String gender,
                                 @RequestParam("course") String course,
                                 @RequestParam("address") String address,
                                 HttpSession session, Model model) {
        String adminUsername = (String) session.getAttribute("admin_username");
        if (adminUsername == null) {
            return "redirect:/admin/login";
        }
        // Ensure passwords match
        if (!password.equals(repassword)) {
            model.addAttribute("error", "Passwords do not match");
            return "admin_add_user";  // Return to the form with an error message
        }

        // Create new user
        User user = new User();
        user.setName(name);
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(password);
        user.setPhone(phone);
        user.setState(state);
        user.setDistrict(district);
        user.setCity(city);
        user.setDob(dob);
        user.setGender(gender);
        user.setCourse(course);
        user.setAddress(address);

        adminServices.addUser(user);

        return "redirect:/admin/dashboard";
    }

    @GetMapping("/edit_user/{id}")
    public String showEditUserForm(@PathVariable Long id, HttpSession session, Model model) {
        String adminUsername = (String) session.getAttribute("admin_username");
        if (adminUsername == null) {
            return "redirect:/admin/login";
        }

        User user = adminServices.getUserById(id);
        if (user == null) {
            model.addAttribute("error", "User not found");
            return "admin_dashboard";
        }
        model.addAttribute("user", user);
        model.addAttribute("page", "admin_edit_profile");
        return "admin_edit_profile";  // Refers to admin_edit_profile.html in the templates folder
    }

    @PostMapping("/update_user")
    public String updateUser(@RequestParam("id") Long id,
                             @RequestParam("name") String name,
                             @RequestParam("username") String username,
                             @RequestParam("email") String email,
                             @RequestParam("phone") String phone,
                             @RequestParam("state") String state,
                             @RequestParam("district") String district,
                             @RequestParam("city") String city,
                             @RequestParam("dob") String dob,
                             @RequestParam("gender") String gender,
                             @RequestParam("course") String[] course,
                             @RequestParam("address") String address,
                             HttpSession session, Model model) {
        String adminUsername = (String) session.getAttribute("admin_username");
        if (adminUsername == null) {
            return "redirect:/admin/login";
        }

        User user = adminServices.getUserById(id);
        if (user == null) {
            model.addAttribute("error", "User not found");
            return "admin_edit_profile";
        }
        user.setName(name);
        user.setUsername(username);
        user.setEmail(email);
        user.setPhone(phone);
        user.setState(state);
        user.setDistrict(district);
        user.setCity(city);
        user.setDob(dob);
        user.setGender(gender);
        user.setCourse(String.join(", ", course));  // Convert array to comma-separated string
        user.setAddress(address);

        adminServices.updateUser(user);

        return "redirect:/admin/dashboard";
    }

    @GetMapping("/delete_user/{id}")
    public String deleteUser(@PathVariable Long id, HttpSession session, Model model) {
        String adminUsername = (String) session.getAttribute("admin_username");
        if (adminUsername == null) {
            return "redirect:/admin/login";
        }

        String result = adminServices.deleteUserById(id);
        model.addAttribute("message", result);  // Add a message attribute to display success or error messages

        return "redirect:/admin/dashboard";  // Redirect to the admin dashboard after deletion
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();  // Invalidate the session
        return "redirect:/admin/login";  // Redirect to admin login page after logout
    }
}
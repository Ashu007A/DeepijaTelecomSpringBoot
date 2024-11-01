package com.example.deepijaTel.Controllers;

import com.example.deepijaTel.Models.User;
import com.example.deepijaTel.Services.UserServices;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserControllers {

    private static final Logger logger = LoggerFactory.getLogger(UserControllers.class);

    @Autowired
    private UserServices userService;

    // Login
    @GetMapping("/user_login")
    public String showLoginForm(Model model) {
        model.addAttribute("page", "user_login");
        return "user_login";  // Refers to user_login.html in the templates folder
    }

    @PostMapping("/user_login")
    public String loginUser(@RequestParam String username, @RequestParam String password, HttpSession session, Model model) {
        logger.info("Login attempt for username: {}", username);
        try {
            String result = userService.loginUser(username, password);
            if (result.equals("Login successful!")) {
                session.setAttribute("username", username);
                logger.info("Login successful for username: {}", username);
                return "redirect:/dashboard";
            } else {
                logger.warn("Login failed for username: {}", username);
                model.addAttribute("error", result);
                return "user_login";
            }
        } catch (Exception e) {
            logger.error("Unexpected error during login for username: {}", username, e);
            model.addAttribute("error", "An unexpected error occurred.");
            return "user_login";
        }
    }

    // Dashboard
    @GetMapping("/dashboard")
    public String showDashboard(Model model, HttpSession session) {
        try {
            String username = (String) session.getAttribute("username");
            logger.info("Session username: {}", username);
            if (username == null) {
                return "redirect:/user_login";
            }
            User user = userService.getUserByUsername(username);
            logger.info("Retrieved User: {}", user);
            if (user == null) {
                logger.error("User not found for username: {}", username);
                return "redirect:/user_login";
            }
            model.addAttribute("user", user);
            model.addAttribute("page", "dashboard");
            logger.info("User details added to model: {}", user);
            return "dashboard";
        } catch (Exception e) {
            logger.error("Unexpected error in DashboardController", e);
            model.addAttribute("error", "An unexpected error occurred.");
            return "error";
        }
    }

    // Registration
    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("page", "register");
        return "register";  // Refers to register.html in the templates folder
    }

    @PostMapping("/register")
    public String registerUser(User user, Model model) {
        String result = userService.registerUser(user);
        if (result.equals("New account created successfully!")) {
            return "redirect:/user_login";
        } else {
            model.addAttribute("error", result);
            return "register";
        }
    }

    // Picture upload
//    @PostMapping("/upload_profile_picture")
//    public ResponseEntity<String> handleFileUpload(@RequestParam("profile_picture") MultipartFile file, @RequestParam("username") String username) {
//        if (file.isEmpty()) {
//            return new ResponseEntity<>("Please select a file to upload", HttpStatus.BAD_REQUEST);
//        }
//        try {
//            // Ensure the directory exists
//            Path uploadDirectory = Paths.get("uploads", "profile_pics");
//            if (!Files.exists(uploadDirectory)) {
//                Files.createDirectories(uploadDirectory);
//            }
//            Path targetFile = uploadDirectory.resolve(username + ".jpg");
//            file.transferTo(targetFile.toFile());
//            return new ResponseEntity<>("You successfully uploaded " + file.getOriginalFilename() + "!", HttpStatus.OK);
//        } catch (IOException e) {
//            return new ResponseEntity<>("Failed to upload " + file.getOriginalFilename() + " => " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }

    // Add Edit Profile GetMapping
    @GetMapping("/edit_profile")
    public String showEditProfileForm(HttpSession session, Model model) {
        String username = (String) session.getAttribute("username");
        if (username == null) {
            return "redirect:/user_login";
        }
        User user = userService.getUserByUsername(username);
        model.addAttribute("user", user);
        model.addAttribute("page", "edit_profile");
        return "edit_profile";  // Refers to edit_profile.html in the templates folder
    }

    // Add Edit Profile PostMapping
    @PostMapping("/edit_profile")
    public String updateProfile(User user, HttpSession session, Model model) {
        String username = (String) session.getAttribute("username");
        if (username == null) {
            return "redirect:/user_login";
        }
        userService.updateUserByUsername(username, user);
        return "redirect:/dashboard";  // Redirect to dashboard after successful update
    }

    // Delete Account GetMapping
    @GetMapping("/delete_account")
    public String showDeleteAccountForm(HttpSession session, Model model) {
        String username = (String) session.getAttribute("username");
        if (username == null) {
            return "redirect:/user_login";
        }
        model.addAttribute("username", username);
        model.addAttribute("page", "delete_account");
        return "delete_account";  // Refers to delete_account.html in the templates folder
    }

    // Delete Account PostMapping
    @PostMapping("/delete_account")
    public String deleteAccount(HttpSession session, Model model) {
        String username = (String) session.getAttribute("username");
        if (username == null) {
            return "redirect:/user_login";
        }
        userService.deleteUserByUsername(username);
        session.invalidate();  // Invalidate session
        return "redirect:/user_login";  // Redirect to login after account deletion
    }

    // Contact GetMapping
    @GetMapping("/contact")
    public String showContactPage(Model model) {
        model.addAttribute("page", "contact");
        return "contact";  // Refers to contact.html in the templates folder
    }

    // Change Username/Password GetMapping
    @GetMapping("/change_username_password")
    public String showChangeUsernamePasswordPage(Model model) {
        model.addAttribute("page", "change_user_password");
        return "change_username_password";  // Refers to change_username_password.html in the templates folder
    }

    // Add Change Username Code
    @PostMapping("/process_username_change")
    public String processUsernameChange(@RequestParam("new_username") String newUsername, HttpSession session, Model model) {
        String oldUsername = (String) session.getAttribute("username");
        if (oldUsername == null) {
            return "redirect:/user_login";
        }
        String result = userService.changeUsername(oldUsername, newUsername);
        if (result.equals("Username changed successfully!")) {
            session.setAttribute("username", newUsername); // Update session with new username
            return "redirect:/user_login";
        } else {
            model.addAttribute("error", result);
            return "change_username_password";
        }
    }

    // Add Change Password Code
    @PostMapping("/process_password_change")
    public String processPasswordChange(@RequestParam("old_password") String oldPassword, @RequestParam("new_password") String newPassword, @RequestParam("confirm_password") String confirmPassword, HttpSession session, Model model) {
        String username = (String) session.getAttribute("username");
        if (username == null) {
            return "redirect:/user_login";
        }
        String result = userService.changePassword(username, oldPassword, newPassword, confirmPassword);
        if (result.equals("Password changed successfully!")) {
            return "redirect:/user_login";
        } else {
            model.addAttribute("error", result);
            return "change_username_password";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();  // Invalidate the session
        return "redirect:/user_login";  // Redirect to login page after logout
    }
}
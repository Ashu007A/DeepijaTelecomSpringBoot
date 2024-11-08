package com.example.deepijaTel.Controllers_REST_APIs;

import com.example.deepijaTel.Models.Primary.User;
import com.example.deepijaTel.Services.AdminServices;
import com.example.deepijaTel.Services.UserServices;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserApiControllers {

    @Autowired
    private UserServices userService;

    @Autowired
    private AdminServices adminServices;

    @GetMapping("/details")
    @ResponseBody
    public User getUserDetails(HttpSession session) {
        String username = (String) session.getAttribute("username");
        if (username == null) {
            return null;
        }
        return userService.getUserByUsername(username);
    }
    // http://localhost:8080/api/user/details

    @RequestMapping(value = "/details/json", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public User getUserDetailsJson(HttpSession session) {
        String username = (String) session.getAttribute("username");
        if (username == null) {
            return null;
        }
        return userService.getUserByUsername(username);
    }
    // http://localhost:8080/api/user/details/json

    @PostMapping("/login")
    @ResponseBody
    public String loginUser(@RequestParam String username, @RequestParam String password, HttpSession session) {
        String result = userService.loginUser(username, password);
        if (result.equals("Login successful!")) {
            session.setAttribute("username", username);
            return "Login successful! Session ID: " + session.getId();
        } else {
            return "Login failed: " + result;
        }
    }
    // http://localhost:8080/api/user/login

    @GetMapping("/{id}")
    @ResponseBody
    public User getUserById(@PathVariable Long id) {
        return adminServices.getUserById(id);
    }
    // http://localhost:8080/api/user/{id}

    @PostMapping("/update")
    @ResponseBody
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
                             @RequestParam("course") String course,
                             @RequestParam("address") String address) {
        User user = adminServices.getUserById(id);
        if (user == null) {
            return "User not found";
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
        user.setCourse(course);
        user.setAddress(address);

        adminServices.updateUser(user);
        return "User updated successfully!";
    }
    // http://localhost:8080/api/user/update

    @DeleteMapping("/{id}")
    @ResponseBody
    public String deleteUserById(@PathVariable Long id) {
        return adminServices.deleteUserById(id);
    }
    // http://localhost:8080/api/user/{id}
}

package com.example.deepijaTel.Controllers_REST_APIs;

import com.example.deepijaTel.Models.User;
import com.example.deepijaTel.Services.UserServices;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserApiControllers {

    @Autowired
    private UserServices userService;

    @GetMapping("/api/user/details")
    @ResponseBody
    public User getUserDetails(HttpSession session) {
        String username = (String) session.getAttribute("username");
        if (username == null) {
            return null;
        }
        return userService.getUserByUsername(username);
    }
    // http://localhost:8080/api/user/details

    @RequestMapping(value = "/api/user/details/json", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public User getUserDetailsJson(HttpSession session) {
        String username = (String) session.getAttribute("username");
        if (username == null) {
            return null;
        }
        return userService.getUserByUsername(username);
    }
    // http://localhost:8080/api/user/details/json
}

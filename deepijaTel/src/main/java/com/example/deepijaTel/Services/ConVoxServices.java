package com.example.deepijaTel.Services;

import com.example.deepijaTel.Models.ConVoxLogin;

public interface ConVoxServices {
    ConVoxLogin findByUsername(String username);
    ConVoxLogin saveUser(ConVoxLogin user);
}
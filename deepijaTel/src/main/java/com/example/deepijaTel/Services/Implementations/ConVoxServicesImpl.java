package com.example.deepijaTel.Services;

import com.example.deepijaTel.Models.ConVoxLogin;
import com.example.deepijaTel.Repositories.ConVoxRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConVoxServicesImpl implements ConVoxServices {

    @Autowired
    private ConVoxRepository convoxRepository;

    @Override
    public ConVoxLogin findByUsername(String username) {
        return convoxRepository.findByUsername(username);
    }

    @Override
    public ConVoxLogin saveUser(ConVoxLogin user) {
        return convoxRepository.save(user);
    }
}
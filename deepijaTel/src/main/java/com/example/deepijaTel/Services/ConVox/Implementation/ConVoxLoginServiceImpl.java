package com.example.deepijaTel.Services.ConVox.Implementation;

import com.example.deepijaTel.Models.Secondary.ConVoxLogin;
import com.example.deepijaTel.Repositories.Secondary.ConVoxRepository;
import com.example.deepijaTel.Services.ConVox.ConVoxLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConVoxLoginServiceImpl implements ConVoxLoginService {

    @Autowired
    private ConVoxRepository convoxRepository;

    @Override
    public ConVoxLogin saveUser(ConVoxLogin user) {
        return convoxRepository.save(user);
    }
}
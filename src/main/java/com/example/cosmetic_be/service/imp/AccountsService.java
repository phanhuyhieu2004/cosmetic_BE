package com.example.cosmetic_be.service.imp;

import com.example.cosmetic_be.model.Accounts;
import com.example.cosmetic_be.repository.IAccountsRepository;
import com.example.cosmetic_be.service.IAccountsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service

public class AccountsService implements IAccountsService {
    @Autowired
    private IAccountsRepository iAccountsRepository;
    @Override
    public Iterable<Accounts> findAll() {
        return null;
    }

    @Override
    public Optional<Accounts> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Accounts save(Accounts accounts) {
        return null;
    }

    @Override
    public void remove(Long id) {

    }

}

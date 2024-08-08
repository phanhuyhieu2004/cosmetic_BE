package com.example.cosmetic_be.service.imp;

import com.example.cosmetic_be.model.Accounts;
import com.example.cosmetic_be.repository.IAccountsRepository;
import com.example.cosmetic_be.service.IAccountsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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

    public Accounts register(String name, String pass) {
        if (iAccountsRepository.findByName(name) != null) {
            throw new RuntimeException("Tài khoản đã tồn tại");
        }

        Accounts accounts = new Accounts();
        accounts.setName(name);
        accounts.setPass(pass);
        accounts.setCreatedAt(LocalDateTime.now());
        accounts.setUpdatedAt(LocalDateTime.now());

        accounts.setRole(1);
        return iAccountsRepository.save(accounts);
    }

    public Accounts login(String name, String pass) {

        Accounts accounts = iAccountsRepository.findByName(name);
        if (accounts == null) {
            throw new RuntimeException("Không có tài khoản");
        }
        accounts.getPass().equals(pass);
        return accounts;
    }
}

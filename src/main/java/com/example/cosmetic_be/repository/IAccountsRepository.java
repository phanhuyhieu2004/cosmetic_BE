package com.example.cosmetic_be.repository;

import com.example.cosmetic_be.model.Accounts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAccountsRepository extends JpaRepository<Accounts,Long> {

Accounts findByName(String name);

}

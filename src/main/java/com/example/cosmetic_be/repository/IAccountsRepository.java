package com.example.cosmetic_be.repository;

import com.example.cosmetic_be.model.Accounts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface IAccountsRepository extends JpaRepository<Accounts,Long> {

Accounts findByName(String name);
@Query("SELECT COUNT(a) FROM Accounts a WHERE a.createdAt")
long countNewAccounts(LocalDateTime startDate,LocalDateTime endTime);
}

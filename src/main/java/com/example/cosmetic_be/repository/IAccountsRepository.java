package com.example.cosmetic_be.repository;

import com.example.cosmetic_be.model.Accounts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Repository
public interface IAccountsRepository extends JpaRepository<Accounts,Long> {

Accounts findByName(String name);
    // Đếm số lượng người dùng theo ngày cụ thể
    @Query("SELECT COUNT(a) FROM Accounts a  WHERE DATE(a.createdAt) = :date  AND a.role=1")
    BigDecimal countAccountsByDate(@Param("date") LocalDate date);

    // Đếm số lượng người dùng theo tháng cụ thể
    @Query("SELECT COUNT(a) FROM Accounts a  WHERE DATE(a.createdAt) = :month AND YEAR(a.createdAt) = :year  AND a.role=1")
    BigDecimal countAccountsByMonth(@Param("month") int month, @Param("year") int year);

    // Đếm số lượng người dùng theo năm cụ thể
    @Query("SELECT COUNT(a) FROM Accounts a  WHERE YEAR(a.createdAt) = :year  AND a.role=1")
    BigDecimal countAccountsByYear(@Param("year") int year);
}


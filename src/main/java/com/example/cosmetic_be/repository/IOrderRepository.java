package com.example.cosmetic_be.repository;

import com.example.cosmetic_be.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface IOrderRepository extends JpaRepository<Order, Long> {

    @Query("SELECT SUM(o.totalPrice) FROM Order o WHERE DATE(o.createdAt) = :date AND o.paymentStatus='Hoàn thành'")
    BigDecimal calculateRevenueByDate(@Param("date") LocalDate date);

    @Query("SELECT SUM(o.totalPrice) FROM Order o WHERE YEAR(o.createdAt) = :year AND MONTH(o.createdAt) = :month AND o.paymentStatus='Hoàn thành'")
    BigDecimal calculateRevenueByMonth(@Param("month") int month, @Param("year") int year);

    @Query("SELECT SUM(o.totalPrice) FROM Order o WHERE YEAR(o.createdAt) = :year AND o.paymentStatus='Hoàn thành'")
    BigDecimal calculateRevenueByYear(@Param("year") int year);

    List<Order> findByAccountId(Long accountId);
    @Query(value = "SELECT " +
            "SUM(CASE WHEN o.payment_status = 'Hoàn thành' THEN 1 ELSE 0 END) AS completedCount, " +
            "SUM(CASE WHEN o.payment_status = 'Hủy' THEN 1 ELSE 0 END) AS cancelledCount, " +
            "SUM(CASE WHEN o.payment_status = 'Đang xử lý' THEN 1 ELSE 0 END) AS processingCount " +
            "FROM orders o",nativeQuery = true)
    Object[] countOrdersBySpecificStatuses();
}

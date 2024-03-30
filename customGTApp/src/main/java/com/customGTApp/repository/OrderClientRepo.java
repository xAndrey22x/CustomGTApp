package com.customGTApp.repository;

import com.customGTApp.model.OrderClient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderClientRepo extends JpaRepository<OrderClient, Long> {
}

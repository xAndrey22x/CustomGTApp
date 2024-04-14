package com.customGTApp.data.repository;

import com.customGTApp.model.OrderOptions;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderOptionsRepo extends JpaRepository<OrderOptions, Long> {
    /**
     * Find OrderOptions by orderClientId
     * @param orderClientId the orderClientId to search for
     * @return OrderOptions
     */
    Optional<OrderOptions> findByOrderClientId(Long orderClientId);
}

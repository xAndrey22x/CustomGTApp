package com.customGTApp.data.repository;

import com.customGTApp.model.OrderOption;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderOptionRepo extends JpaRepository<OrderOption, Long> {
    /**
     * Find OrderOption by orderClientId
     * @param orderClientId the orderClientId to search for
     * @return OrderOption
     */
    Optional<OrderOption> findByOrderClientId(Long orderClientId);
}
